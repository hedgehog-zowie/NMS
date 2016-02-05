package com.iuni.nms.common;

/**
 * 返回码
 * @author Nicholas
 *         Email:   nicholas.chen@iuni.com
 */
public enum ReturnCode {
    /**
     * 成功
     */
    SUCCESS("000000","成功"),
    /**
     * 参数错误
     */
    ERROR_PARAM_NO_OBJECT("100001","监控对象不存在"),
    ERROR_PARAM_NO_ITEM("100002","监控项不存在"),
    ERROR_PARAM_NO_PROPERTY("100003","监控项属性不存在"),
    /**
     * 系统错误
     */
    ERROR_SYSTEM("200001", "系统错误"),
    ;

    private String code;
    private String msg;

    ReturnCode(String Code, String Msg) {
        this.code = Code;
        this.msg = Msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
