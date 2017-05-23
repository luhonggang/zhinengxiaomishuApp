package com.shanlin.intelligent.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配 url 扫描 xml
 */
@CacheConfig(cacheNames = "docheckUrls")
@Configuration
public class MatchUrlUtils {

	private Pattern pattern = null;
	private Matcher match = null;
	private boolean flag = false;
	@Value("${springBoot.notcheckLoginUrl}")
	private String checkLoginUrls;
	/**
	 * 
	 * @param url
	 * @param
	 * @return
	 * 
	 *         正则匹配url
	 */
	//@Cacheable(value = "docheckUrls",condition = "#url='/user/getUser'")
	public boolean checkUrls(String url){
		return isMactchUrl(checkLoginUrls, url);
	}

	private boolean isMactchUrl(String urls, String url) {
		Logger.info(this,"check url");
		// 判断含有 url
		flag = false;	

		if (null == urls) {
			return flag;
		}
		List<String> urlsList = Arrays.asList(urls.split(","));

		urlsList.forEach(params -> {
			// 匹配成功，跳出循环
			if (url.contentEquals(params)) {
				flag = true;
				return;
			}
		});
		if (flag) {

			return flag;

		} else {
			// 正则匹配
			urlsList.forEach(param -> {
				pattern = Pattern.compile(param);
				match = pattern.matcher(url);
				// 匹配成功，跳出循环
				if (match.matches()) {
					flag = true;
					return;
				}

			});

		}

		return flag;

	}

}
