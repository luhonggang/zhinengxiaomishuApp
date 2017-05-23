package com.shanlin.intelligent.exception;

import com.shanlin.intelligent.error.Error;

/**
 * Created by shanlin on 2017/4/6.
 */
public class OutTimeTokenException extends MDPException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7051836414685780479L;

	@Override
    public Error getCode() {
        return Error.OutTimeTokenException;
    }

    @Override
    public String getErrorMessage() {
        return String.format(Error.OutTimeTokenException.getMessage());
    }
}
