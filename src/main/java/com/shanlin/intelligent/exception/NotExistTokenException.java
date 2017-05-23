package com.shanlin.intelligent.exception;


import com.shanlin.intelligent.error.Error;
public class NotExistTokenException extends MDPException{



	/**
	 * 
	 */
	private static final long serialVersionUID = 8867783841194312911L;

	public NotExistTokenException() {
		
	}

	@Override
	public Error getCode() {
		return Error.NotExistTokenException;
	}

	@Override
	public String getErrorMessage() {
		return String.format(getCode().getMessage());
	}

}
