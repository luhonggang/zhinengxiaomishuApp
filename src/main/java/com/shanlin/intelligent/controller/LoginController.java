package com.shanlin.intelligent.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanlin.intelligent.error.Error;
import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.request.LoginRequest;
import com.shanlin.intelligent.response.LoginResponse;
import com.shanlin.intelligent.response.BaseResponse;
import com.shanlin.intelligent.service.LoginService;
import com.shanlin.intelligent.utils.UUIDUtils;
import com.shanlin.intelligent.utils.UserContext;
/**
 * 类注释
 *
 * @author: ww
 * @date: 2017/5/9
 * @time: 17:10
 * @see: 链接到其他资源
 * @since: 1.0
 */

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Value("${oper.expireTime}")
	private Long expireTime;//过期时间
	
	@Value("${oper.defaultPassword}")
	private String defaultPassword;//默认密码
	
	@Value("${oper.errorCount}")
	private Integer errorCount;//密码错误次数
	

	
	
	//登出
	@GetMapping("/out")
	@ResponseBody
	public BaseResponse<?> loginOut() {
        String userNo = UserContext.getCurrentuserNo().get();
        log.info(this+ "userNo loginout :" + userNo);
        UserEntity user = loginService.getUserByUserNo(userNo);
        if (null != user) {
        	user.setExpireTime(System.currentTimeMillis() - 1);
        	loginService.updateByPrimaryKey(user);
            // 清理用户数据
            UserContext.clean();
        }
        return BaseResponse.ok();
    }
}
