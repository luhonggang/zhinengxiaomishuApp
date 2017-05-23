package com.shanlin.intelligent.utils;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ShanLin on 2017/5/9.
 */
public final class UserContext {
	private UserContext() {

	}

	private static UserContext userContext = new UserContext();

	private ThreadLocal<String> userNo = new ThreadLocal<>();

	private ThreadLocal<String> userName = new ThreadLocal<>();

	public static void init(String userNo, String userName) {
		if (StringUtils.isNoneEmpty(userContext.userNo.get()) || StringUtils.isNoneEmpty(userContext.userName.get())) {
			// TODO
			// can't rset the &userName
		}
		userContext.userNo.set(userNo);
		userContext.userName.set(userName);
	}

	public static Optional<String> getCurrentuserName() {
		if (null != userContext.userName.get()) {
			return Optional.of(userContext.userName.get());
		}
		return Optional.empty();
	}

	public static Optional<String> getCurrentuserNo() {
		if (null != userContext.userNo.get()) {
			return Optional.of(userContext.userNo.get());
		}
		return Optional.empty();
	}

	// TODO should be invoke in last filter
	public static void clean() {
		userContext.userName.remove();
		userContext.userNo.remove();
	}
}
