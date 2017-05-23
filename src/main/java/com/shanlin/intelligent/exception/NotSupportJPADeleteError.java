package com.shanlin.intelligent.exception;

/**
 * Created by ShanLin on 2017/4/3.
 */
public class NotSupportJPADeleteError extends MDPError {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9152554402313851037L;
	private String methodName;

    public NotSupportJPADeleteError(String methodName) {
        this.methodName = methodName;
    }

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
    
}
