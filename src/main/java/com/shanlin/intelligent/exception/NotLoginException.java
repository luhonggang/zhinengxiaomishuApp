package com.shanlin.intelligent.exception;

import com.shanlin.intelligent.error.Error;

/**
 * Created by shanlin on 2017/4/6.
 */
public class NotLoginException extends  MDPException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6664597933525995502L;

	@Override
    public Error getCode() {
        return Error.NotLoginException;
    }

    @Override
    public String getErrorMessage() {
        return String.format(Error.NotLoginException.getMessage());
    }
}
