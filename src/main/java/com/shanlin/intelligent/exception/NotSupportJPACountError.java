package com.shanlin.intelligent.exception;

/**
 * Created by ShanLin on 2017/4/3.
 */
public class NotSupportJPACountError extends MDPError {
    /**
	 * 
	 */
	private static final long serialVersionUID = -889942550081823627L;
	private String methodName;

    public NotSupportJPACountError(String methodName) {
        this.methodName = methodName;
    }

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
    
}
