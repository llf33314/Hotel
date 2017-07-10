package com.gt.hotel.emuns;

/**
 * @author zhangmz
 * @create 2017/6/12
 */
public enum ResponseCode {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(1001, "需要登录"),
    AUTHENTICATION(10, "非法认证"),
    JWT_TOKEN_EXPIRED(11, "TOKEN失效");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
