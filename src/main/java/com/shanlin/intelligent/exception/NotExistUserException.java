package com.shanlin.intelligent.exception;

import com.shanlin.intelligent.error.Error;
/**
 * Created by shanlin on 2017/4/6.
 */
public class NotExistUserException extends MDPException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4367670873598595923L;

	private String userNo = null;

	public NotExistUserException(String userNo) {
		this.userNo = userNo;
	}
	public NotExistUserException() {
		this.userNo = "";
	}

	@Override
	public Error getCode() {
		return Error.NotExistUserException;
	}

	@Override
	public String getErrorMessage() {
		return String.format(getCode().getMessage(),this.userNo);
	}
}
