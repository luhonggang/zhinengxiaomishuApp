package com.shanlin.intelligent.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Span;

import com.shanlin.intelligent.error.Error;
import com.shanlin.intelligent.exception.NotExistTokenException;
import com.shanlin.intelligent.exception.NotExistUserException;
import com.shanlin.intelligent.exception.NotLoginException;
import com.shanlin.intelligent.exception.OutTimeTokenException;
import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.response.BaseResponse;
import com.shanlin.intelligent.service.LoginService;
import com.shanlin.intelligent.utils.JsonUtil;
import com.shanlin.intelligent.utils.MatchUrlUtils;
import com.shanlin.intelligent.utils.UserContext;

/**
 * Created by shanlin on 2017/4/6.
 * 
 * 判断 session *

 * 首先判断这个接口需不要session
 * 
 * 不需要直接到过滤
 * 
 * 需要的，要做过滤，判断session是否过期
 * 
 * 
 */
@WebFilter(urlPatterns = "/*")
public class BasicTokenFilter implements Filter {

	@Value("${oper.expireTime}")
	private Long expireTime;

	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private Logger log = LoggerFactory.getLogger(BasicTokenFilter.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private MatchUrlUtils matchUrlUtils;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		long curTime = System.currentTimeMillis();
		String url = httpServletRequest.getServletPath();
		// Logger.debug(this, " url is " + url);
		Object object = MDC.get(Span.TRACE_ID_NAME);
		String tranceId = (object == null ? "" : object.toString());

		Cookie tanceCookie = new Cookie("tranceId", tranceId);
		tanceCookie.setMaxAge(httpServletRequest.getSession().getMaxInactiveInterval());
		httpServletResponse.addCookie(tanceCookie);
		// 匹配不到，不需要带token 的url
		try {
		if (!matchUrlUtils.checkUrls(url)) {//(匹配除了notcheckLoginUrl配置的 URL) 返回为true 说明当前URL和application.yml中的notcheckLoginUrl: /user/getUser的URL相等
			try {
				doCheckToken(httpServletRequest, httpServletResponse, curTime, false);
			} catch (OutTimeTokenException e) {
				log.error(this+ ":token过期:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.OutTimeTokenException.getCode(),
						Error.OutTimeTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotExistUserException e) {
				log.error(this+ ":用户不存在:", e);
				BaseResponse<?>  baseResponse = BaseResponse.fail(Error.NotExistTokenException.getCode(),
						Error.NotExistTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotLoginException e) {
				log.error(this+ ":用户未登录:", e);
				BaseResponse<?>  baseResponse = BaseResponse.fail(Error.NotLoginException.getCode(),
						Error.NotLoginException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotExistTokenException e) {
				log.error(this+":token不存在:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.NotExistTokenException.getCode(),
						Error.NotExistTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (Exception e) {
				log.error(this+ ":其他异常:", e);
				BaseResponse<?>  baseResponse = BaseResponse.failure();
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			}

		} else {
			// 没有带token，可以正常访问,带了token ，需要保存用户数据
			try {
				doCheckToken(httpServletRequest, httpServletResponse, curTime, true);
			} catch (OutTimeTokenException e) {
				log.error(this+":token过期:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.OutTimeTokenException.getCode(),
						Error.OutTimeTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotExistUserException e) {
				log.error(this+ ":用户不存在:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.NotExistTokenException.getCode(),
						Error.NotExistTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotLoginException e) {
				log.error(this+":用户未登录:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.NotLoginException.getCode(),
						Error.NotLoginException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (NotExistTokenException e) {
				log.error(this+ ":token不存在:", e);
				BaseResponse<?> baseResponse = BaseResponse.fail(Error.NotExistTokenException.getCode(),
						Error.NotExistTokenException.getMessage());
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			} catch (Exception e) {
				log.error(this+":其他异常:", e);
				BaseResponse<?> baseResponse = BaseResponse.failure();
				this.setResponseData(httpServletResponse, JsonUtil.toJsonString(baseResponse));
				return;
			}
		}
		chain.doFilter(httpServletRequest, httpServletResponse);
		}
		finally 
        {
			UserContext.clean();
		}
		return;

	}

	@Override
	public void destroy() {
		log.info(this + ":destroy BasicTokenFilter ");
	}

	public void doCheckToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			long curTime, boolean flag) throws IOException {
		// 通过数据库，查询数据，是否存在或者过期
		String token = httpServletRequest.getHeader("x-sljr-session-token");
		log.info(this + ":toke is " + token + " , flag is " + flag);
		// 没有带token
		if (StringUtils.isBlank(token) && flag) {
			return;
		}
		try {
			readWriteLock.writeLock().lock();
			UserEntity userToken = loginService.getUserByToKen(token);
			if (null != userToken) {
				log.debug(this + ":token is not null");
				if (curTime > userToken.getExpireTime()) {
					// 报token 失效
					log.info("token 失效--" + ":curTime is " + curTime + " expireTime is " + userToken.getExpireTime());
					throw new OutTimeTokenException();
				} else {
					// 否则，更新token时间
					userToken.setExpireTime(new Timestamp(System.currentTimeMillis()).getTime() + expireTime);
					loginService.updateByPrimaryKeySelective(userToken);
					// 设置一个本地线程，保存用户信息
					UserEntity userEntity = loginService.selectByPrimaryKey(userToken.getId());
					if (null != userEntity) {
						UserContext.init(userEntity.getUserNo(), userEntity.getUserName());
					} else {
						// 用户不存在
						throw new NotExistUserException(userToken.getUserNo());
					}
				}

			} else {
				// 还未登录
				throw new NotLoginException();
			}

		} finally {
			if (readWriteLock.isWriteLocked()) {
				readWriteLock.writeLock().unlock();
			}

			// UserContext.clean();
		}

	}

	/**
	 * 设置结果值
	 * 
	 * @param httpServletResponse
	 * @param result
	 * @throws IOException
	 */
	public void setResponseData(HttpServletResponse httpServletResponse, String result) throws IOException {
		httpServletResponse.setContentType("application/json; charset=utf-8");
		PrintWriter out = httpServletResponse.getWriter();
		out.print(result);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
