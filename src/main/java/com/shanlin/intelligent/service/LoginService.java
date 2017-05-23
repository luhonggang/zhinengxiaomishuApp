package com.shanlin.intelligent.service;

import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.request.LoginRequest;

/**
 * 类注释
 *
 * @author: ww
 * @date: 2017/5/09
 * @time: 17:04
 * @see: 链接到其他资源
 * @since: 1.0
 */
public interface LoginService {
	


	
	/**
	 * 根据token查询用户
	 * @param toKen
	 * @return
	 */
	UserEntity getUserByToKen(String toKen);
	
	/**
	 * 登录
	 * @param loginRequest
	 * @return
	 */
	UserEntity getUserBynameAndPassWord(LoginRequest loginRequest);

	int deleteByPrimaryKey(Long id);

	int insert(UserEntity record);

	int insertSelective(UserEntity record);

	UserEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserEntity record);

	int updateByPrimaryKey(UserEntity record);
	
	UserEntity getUserByUserNo(String userNo);
}
