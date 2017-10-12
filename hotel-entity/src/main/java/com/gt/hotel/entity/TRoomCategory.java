package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 房型
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_room_category" )
public class TRoomCategory extends Model< TRoomCategory > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 房型名称
     */
    private String  name;
    /**
     * 房间总数
     */
    @TableField( "room_count" )
    private Integer roomCount;
    /**
     * 酒店ID
     */
    @TableField( "hotel_id" )
    private Integer hotelId;
    /**
     * 门店ID
     */
    @TableField( "store_id" )
    private Integer storeId;
    /**
     * 描述
     */
    private String  desc;
    /**
     * 门市价
     */
    @TableField( "rack_rate" )
    private Integer rackRate;
    /**
     * 押金
     */
    private Integer deposit;
    /**
     * 是否需要计算优惠价：0 需要 1 不需要
     */
    @TableField( "discount_enable" )
    private Integer discountEnable;
    /**
     * 折扣率范围 1 - 100 默认 100(计算方式：85/100=0.85 即85折)，即不打折
     */
    private Integer discount;
    /**
     * 是否开启周末价 0 开启 1 禁用，即显示门市价即可
     */
    @TableField( "weekend_fare_enable" )
    private Integer weekendFareEnable;
    /**
     * 周末价格
     */
    @TableField( "weekend_fare" )
    private Integer weekendFare;
    /**
     * 是否启用 0 默认开启 1 关闭 2 删除标记
     */
    @TableField( "mark_modified" )
    private Integer markModified;
    /**
     * 是否开启早餐券 0 开启 1 关闭
     */
    @TableField( "breakfast_enable" )
    private Integer breakfastEnable;
    /**
     * 早餐数量
     */
    @TableField( "breakfast_quantity" )
    private Integer breakfastQuantity;
    /**
     * 创建者ID
     */
    @TableField( "created_by" )
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField( "created_at" )
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @TableField( "updated_by" )
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField( "updated_at" )
    private Date    updatedAt;

    @Override
    protected Serializable pkVal() {
	return this.id;
    }

    /**
     * 门店对象
     *
     * @author ReverieNight@Foxmail.com
     * @date 2017年10月9日 上午11:46:26
     */
    @Data
    @Api( description = "门店对象" )
    public static class HotelWsWxShopInfoExtend {
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
}
