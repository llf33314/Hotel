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
    public static final String MODULE_HOTEL = "hotel";
    /**
     * 房型
     */
    public static final String MODULE_ROOM_CATEGORY = "roomCategory";
    /**
     * 客房
     */
    public static final String MODULE_ROOM  = "room";

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

    //////////////////////////使用状态 /////////////////////////
    
    /**
     * 0 默认开启
     */
    public static final Integer ENABLED  = 0;
    /**
     * 1 关闭
     */
    public static final Integer CLOSE = 1;
    /**
     * 2 删除标记
     */
    public static final Integer DELETED  = 2;
    
    //////////////////////////字典名 /////////////////////////
    /**
     * 发票
     */
    public static final String DICT_INVOICE  = "发票";
    /**
     * 权限功能
     */
    public static final String DICT_FUNCTION  = "权限功能";
    
    
}
