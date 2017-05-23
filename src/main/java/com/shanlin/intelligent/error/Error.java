package com.shanlin.intelligent.error;

/**
 * Created by shanlin on 2017/4/12.
 *
 * 异常同意放置地方
 */
public  enum  Error {
	 SUCCESS(ErrorCode.SUUCCESS, "success")
    ,MDPException(ErrorCode.UNKNOW_EXCEPTION,"未知异常")
    ,NotExistUserException(ErrorCode.NOT_EXIST_USER,"用户或密码错误")
    ,OutTimeTokenException(ErrorCode.OUT_TIME_TOKEN,"token 失效")
    ,NotLoginException(ErrorCode.NOT_LOGIN,"用户未登录,请重新登录")
    ,NotExistTokenException(ErrorCode.NOT_EXIST_TOKEN,"不存在的token")
    ,NotParamException(ErrorCode.Not_PARAM_USER,"参数不全")
    ,PassWordException(ErrorCode.PassWordException,"密码为默认密码")
    ,ExceedCountException(ErrorCode.ExceedCountException,"用户当天错误次数超过20次")
    ,SAVESTOREUSERFAILException(ErrorCode.ExceedCountException,"门店信息保存失败")
    ,UPDATESTOREUSERFAILException(ErrorCode.ExceedCountException,"门店信息更新失败")
    ,NOTNULLSTORECODEException(ErrorCode.NOTNULLSTORECODEException,"门店编码不能为空")
    ;

    private String code;
    private String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
