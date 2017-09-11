package com.gt.hotel.constant;

/**
 * Session 公共的Key
 */
public interface CommonSessionConst {

    /**
     * 商家账户在session key 当前登录的Bus用户
     */
    String CURRENT_BUS_USER      = "business_key";
    /**
     * 员工session 列表的值 获取员工信息
     */
    String CURRENT_TCOMMON_STAFF = "TCommonStaff";
    /**
     * 登录的形式
     */
    String SESSION_LOGIN_STYLE   = "loginStyle";
    /**
     * 签名
     */
    String SIGNKEY               = "WXMP2017";
}
