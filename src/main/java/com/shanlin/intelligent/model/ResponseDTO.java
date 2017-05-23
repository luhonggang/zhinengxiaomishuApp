package com.shanlin.intelligent.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 类注释
 *
 * @author: hz
 * @date: 2017/4/28
 * @time: 09:21
 * @see: 链接到其他资源
 * @since: 1.0
 */
@JsonSerialize
public class ResponseDTO<T> {

    private boolean isSuccess;

    private String code;

    private String messages;


    private T data;

    private Object sumObject;

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

    public void getIsSuccess(boolean isSuccess){
        this.isSuccess = isSuccess;
    }


}
