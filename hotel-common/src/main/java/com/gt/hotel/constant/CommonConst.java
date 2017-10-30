package com.gt.hotel.constant;

/**
 * 酒店公共常量
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/09/07
 */
public final class CommonConst {

    /****************************** 模块类型 ******************************/

    /**
     * 酒店
     */
    public static final String MODULE_HOTEL         = "HOTEL";
    /**
     * 房型
     */
    public static final String MODULE_ROOM_CATEGORY = "ROOM_CATEGORY";
    /**
     * 客房
     */
    public static final String MODULE_ROOM          = "ROOM";

    /****************************** 活动状态 ******************************/

    /**
     * 未开始
     */
    public static final Integer NOT_START  = 0;
    /**
     * 进行中
     */
    public static final Integer PROCESSING = 1;
    /**
     * 已结束
     */
    public static final Integer OVER       = 2;
    /**
     * 停止
     */
    public static final Integer STOP       = 3;

    /****************************** 活动类型 ******************************/

    /**
     * 类型（0=秒杀）
     */
    public static final Integer SECKILL = 0;
    /**
     * 类型（1=团购）
     */
    public static final Integer GROUP   = 1;

    /****************************** 客房类型 ******************************/

    /**
     * 类型（2=特价房）
     */
    public static final Integer SPECIAL = 2;
    /**
     * 类型（3=时租房）
     */
    public static final Integer TIME    = 3;

    /****************************** 支付方式 ******************************/

    /**
     * 支付方式(1：在线支付)
     */
    public static final Integer ONLINE_PAY  = 1;
    /**
     * 支付方式(2：到店支付)
     */
    public static final Integer OFFLINE_PAY = 2;
    /**
     * 支付方式(3：1&2)
     */
    public static final Integer ALL_PAY     = 3;
    
    /****************************** 支付状态 ******************************/
    
    /**
     * 支付状态(=0 待支付)
     */
    public static final Integer PAY_STATUS_UNPAID = 0;
    /**
     * 支付状态(=1 已支付 )
     */
    public static final Integer PAY_STATUS_PAID = 1;
    /**
     * 支付状态(=2 退款中 )
     */
    public static final Integer PAY_STATUS_UNREFUNDS = 2;
    /**
     * 支付状态(=3 已退款 )
     */
    public static final Integer PAY_STATUS_REFUNDS = 3;

    /****************************** 使用状态  ******************************/

    /**
     * 0 默认开启
     */
    public static final Integer ENABLED = 0;
    /**
     * 1 关闭
     */
    public static final Integer CLOSE   = 1;
    /**
     * 2 删除标记
     */
    public static final Integer DELETED = 2;

    /****************************** 字典名  ******************************/
    /**
     * 发票
     */
    public static final Integer DICT_INVOICE  = 4;
    /**
     * 权限功能
     */
    public static final Integer DICT_FUNCTION = 3;

    /****************************** 入住方式 0 预订订单(预约订单: 网上|电话|移动端 预订过产生了一条预订消息则为预订订单)  1 到店入住(直接办理入住)  ******************************/
    /**
     * 预订订单
     */
    public static final Integer CHECK_IN_WAY_ONLINE  = 0;
    /**
     * 到店入住
     */
    public static final Integer CHECK_IN_WAY_OFFLINE = 1;

    /****************************** 来源 1 后台(线下订单) 2 ERP 3 移动端(H5) 4 小程序 ，默认 0 未知来源 ******************************/
    
    /**
     * 0 未知来源
     */
    public static final Integer SOURCE_UNKNOWN = 0;
    /**
     * 1 后台(线下订单)
     */
    public static final Integer SOURCE_BACK = 1;
    /**
     * 2 ERP 
     */
    public static final Integer SOURCE_ERP = 2;
    /**
     * 3 移动端(H5)
     */
    public static final Integer SOURCE_MOBILE = 3;
    /** 
     * 4 小程序 
     */
    public static final Integer SOURCE_MINI_PROGRAM = 4;

    /****************************** 订单流程状态 =0 处理中 =1 已确认 =2 已取消 =3 已完成 默认0 ******************************/
    
    /**
     * 0 处理中
     */
    public static final Integer ORDER_PROCESSING = 0;
    /**
     * 1 已确认
     */
    public static final Integer ORDER_CONFIRMED = 1;
    /**
     * 2 已取消
     */
    public static final Integer ORDER_CANCALLED = 2;
    /**
     * 3 已完成
     */
    public static final Integer ORDER_COMPLETED = 3;
    
}
