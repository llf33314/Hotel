package com.gt.hotel.constant;

/**
 * 客房状态
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/09/28
 */
public final class RoomStatusConst {
    //////////////  字典值 1	空房,2	锁定,3	在住,4	清洁,5	维护 //////////////

    /**
     * 空房
     */
    public static final String VACANT_ROOM = "1";
    /**
     * 锁
     */
    public static final String LOCK        = "2";
    /**
     * 在住
     */
    public static final String STAY_IN_THE = "3";
    /**
     * 已预订
     */
    public static final String RESERVED    = "";
    /**
     * 清洁
     */
    public static final String CLEAN       = "4";
    /**
     * 维护
     */
    public static final String MAINTAIN    = "5";

}
