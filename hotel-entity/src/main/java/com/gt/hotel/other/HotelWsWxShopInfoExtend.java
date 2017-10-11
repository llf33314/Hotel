package com.gt.hotel.other;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 门店对象
 *
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 上午11:46:26
 */
@Data
@Api( description = "门店对象" )
public class HotelWsWxShopInfoExtend {
    private Integer id;
    /**
     * 多粉内部ID
     */
    private String  sid;
    /**
     * 微信的门店ID
     */
    private String  poiid;
    /**
     * 商家id
     */
    private Integer busId;
    /**
     * 门店名
     */
    private String  businessName;
    /**
     * 分店名
     */
    private String  branchName;
    /**
     * 门店类型主类型
     */
    private String  categories;
    /**
     * 二级类目
     */
    private String  twoCategories;
    /**
     * 三级类目
     */
    private String  threeCategories;
    /**
     * 电话(固定电话需加区号；区号、分机号均用“-”连接)
     */
    private String  telephone;
    /**
     * 人均价格(大于零的整数，须如实填写，默认单位为人民币)
     */
    private Integer avgPrice;
    /**
     * 开始营业时间
     */
    private String  startTime;
    /**
     * 关门时间
     */
    private String  endTime;
    /**
     * 特色服务
     */
    private String  special;
    /**
     * 简介
     */
    private String  introduction;
    /**
     * 推荐品
     */
    private String  recommend;
    /**
     * 门店所在的省份
     */
    private String  province;
    /**
     * 城市
     */
    private String  city;
    /**
     * 地区
     */
    private String  district;
    /**
     * 详细街道地址
     */
    private String  address;
    /**
     * 坐标类型(1 为火星坐标（目前只能选1）)
     */
    private Integer offsetType;
    /**
     * 经度
     */
    private String  longitude;
    /**
     * 纬度
     */
    private String  latitude;
    /**
     * 0：表示还没将门店信息发送到公众号审核，可修改全部内容；1：表示已发送给微信公众号审核  2 已审核通过 3审核通过后重新审核服务信息 4 送审被失败
     */
    private Integer status;
    /**
     * 单位信息
     */
    private String  detail;
    /**
     * 主店1：表示主店
     */
    private Integer mainShop;
    /**
     * 第一张图片
     */
    private String  imageUrl;

    private String createTime;

    private Integer availableState;

    private Integer updateStatus;

    private String adder;

}
