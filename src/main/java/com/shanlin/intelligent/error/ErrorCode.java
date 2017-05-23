package com.shanlin.intelligent.error;

/**
 * Created by shanlin on 2017/4/12. 放置错误码的全局变量
 *
 */
public interface ErrorCode {
	public String SUUCCESS = "000000";// 表示成功
	public String NOT_EXIST_USER = "010003"; // 不存在的用户
	public String OUT_TIME_TOKEN = "010004";// token 失效
	public String NOT_LOGIN = "010005"; // 用户未登录
	public String NOT_EXIST_TOKEN = "010006";// token 不存在
	public String UNKNOW_EXCEPTION = "999999"; // 未知异常
	public String Not_PARAM_USER = "010006";// 参数不全.
	public String PassWordException = "010007";// 密码为默认密码
	public String ExceedCountException = "010008";// 用户当天错误次数超过20次
	public String SAVESTOREUSERFAILException = "010008";//保存门店信息失败
	public String UPDATESTOREUSERFAILException = "010009";//更新门店信息失败
	public String NOTNULLSTORECODEException = "010010";//门店编码不能为空
}
