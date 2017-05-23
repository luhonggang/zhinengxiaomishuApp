package com.shanlin.intelligent.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanlin.intelligent.mapper.UserEntityMapper;
import com.shanlin.intelligent.model.UserEntity;
import com.shanlin.intelligent.request.LoginRequest;
import com.shanlin.intelligent.service.LoginService;
@Service
public class LoginServerImpl implements LoginService {
	
	@Autowired
	private UserEntityMapper userEntityMapper;

	private Logger log = LoggerFactory.getLogger(LoginServerImpl.class);
	/**
	 * 根据token获取用户
	 */
	@Override
	public UserEntity getUserByToKen(String toKen) {
		return userEntityMapper.getUserByToKen(toKen);
	}

	/**
	 * 登录
	 */
	@Override
	public UserEntity getUserBynameAndPassWord(LoginRequest loginRequest) {
		return userEntityMapper.getUserBynameAndPassWord(loginRequest);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userEntityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserEntity record) {
		return userEntityMapper.insert(record);
	}

	@Override
	public int insertSelective(UserEntity record) {
		return userEntityMapper.insertSelective(record);
	}

	@Override
	public UserEntity selectByPrimaryKey(Long id) {
		return userEntityMapper.selectByPrimaryKey(id);
	}

	// 登录成功 给予toKen,登录时间
	@Override
	public int updateByPrimaryKeySelective(UserEntity record) {
		return userEntityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserEntity record) {
		return userEntityMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserEntity getUserByUserNo(String userNo) {
		return userEntityMapper.getUserByUserNo(userNo);
	}


	


}
