package com.shanlin.intelligent.mapper;

import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.request.LoginRequest;

public interface UserEntityMapper {
	
	/**
	 * 根据用户编号查询
	 * @param userNo
	 * @return
	 */
	UserEntity getUserByUserNo(String userNo);

	/**
	 * 根据token查询用户
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity getUserByToKen(String toKen);

	/**
	 * 登录
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity getUserBynameAndPassWord(LoginRequest loginRequest);

	int deleteByPrimaryKey(Long id);

	int insert(UserEntity record);

	int insertSelective(UserEntity record);

	UserEntity selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserEntity record);
	
	int updateByPrimaryKey(UserEntity record);
}