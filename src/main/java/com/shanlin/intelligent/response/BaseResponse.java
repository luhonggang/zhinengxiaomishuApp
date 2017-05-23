package com.shanlin.intelligent.response;

import com.shanlin.intelligent.error.Error;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/28
 * @time: 09:21
 * @see: 链接到其他资源
 * @since: 1.0
 */
public class BaseResponse<T> {

	private boolean isSuccess;

	private String code;

	private String messages;

	private T data;

	private Object sumObject;

	public BaseResponse() {
	}

	public BaseResponse(boolean isSuccess, String code, String messages) {
		super();
		this.isSuccess = isSuccess;
		this.code = code;
		this.messages = messages;
	}

	public BaseResponse(boolean isSuccess, String code, String messages, T data) {
		super();
		this.isSuccess = isSuccess;
		this.code = code;
		this.messages = messages;
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Object getSumObject() {
		return sumObject;
	}

	public void setSumObject(Object sumObject) {
		this.sumObject = sumObject;
	}

	public void getIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public static BaseResponse ok() {
		return new BaseResponse(true, Error.SUCCESS.getCode(), Error.SUCCESS.getMessage());
	}
	
	public static BaseResponse fail(String code, String message) {
		return new BaseResponse(false, code, message);
	}

	public static BaseResponse failure() {
		return new BaseResponse(false, Error.MDPException.getCode(), Error.MDPException.getMessage());
	}
}
