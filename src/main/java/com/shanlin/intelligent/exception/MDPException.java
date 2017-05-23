package com.shanlin.intelligent.exception;

import com.shanlin.intelligent.error.Error;

/**
 * Created by caoyanfei on 16/12/12.
 */
public abstract class MDPException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5702618461006406732L;
	public MDPException() {
    }

    public MDPException(String message) {
        super(message);
    }

    public MDPException(String message, Throwable cause) {
        super(message, cause);
    }

    public MDPException(Throwable cause) {
        super(cause);
    }

    public MDPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public abstract Error getCode();
    public abstract String getErrorMessage();

}
