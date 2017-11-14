package com.gt.hotel.enums;

/**
 * 响应成功Code 类
 *
 * @author zhangmz
 * @create 2017/6/12
 */
public enum ResponseEnums {
    // 100 以内 均为常见错误 例如短信验证 手机号码校验 邮箱发送
    SUCCESS(0, "成功"),
    ERROR(1, "错误"),
    AUTHENTICATION(10, "非法认证"),
    JWT_TOKEN_EXPIRED(11, "TOKEN失效"),
    DATA_DOES_NOT_EXIST(12, "数据不存在"),
    BAD_REQUEST(13, "非法请求"),

    // 1000-1999 定义常见业务错误信息载体
    NEED_LOGIN(1001, "需要登录"),
    SAVE_ERROR(1002, "保存失败"),
    DELETE_ERROR(1003, "删除失败"),
    OPERATING_ERROR(1004, "操作失败"),
    ORDER_STATUS_ERROR(1005, "订单状态错误"),
    PAY_STATUS_ERROR(1006, "支付状态错误"),
    REFUNDS_ERROR(1008, "退款失败"),
    FAILED_TO_OBTAIN_MEMBER_INFORMATION(1007, "获取会员信息失败"),
    BOOK_FAILED(1008, "下单失败"),

    INFRASTRUCTRUE_ERROR(1998, "设施保存失败"),
    IMAGE_ERROR(1999, "图片保存失败"),

    // 30000-39999 为集成第三方API错误信息 例如gt_api
    SIGNATURE_ERROR(3001, "签名错误"),
    //...
    // 9000 以上均为 非项目自定义异常错误
    UNKNOWN_ERROR(9998, "未知错误"),
    SYSTEM_ERROR(9999, "系统错误");


    private final int    code;
    private final String msg;

    ResponseEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
