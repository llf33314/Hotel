package com.gt.hotel.entity;

<<<<<<< HEAD
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

=======
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
/**
 * <p>
 * 房型
 * </p>
 *
<<<<<<< HEAD
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_room_category")
public class TRoomCategory extends Model<TRoomCategory> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 房型名称
     */
	private String name;
    /**
     * 房间总数
     */
	@TableField("room_count")
	private Integer roomCount;
    /**
     * 酒店ID
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 门店ID
     */
	@TableField("store_id")
	private Integer storeId;
    /**
     * 描述
     */
	private String desc;
    /**
     * 门市价
     */
	@TableField("rack_rate")
	private Integer rackRate;
    /**
     * 押金
     */
	private Integer deposit;
    /**
     * 是否需要计算优惠价：0 需要 1 不需要
     */
	@TableField("discount_enable")
	private Integer discountEnable;
    /**
     * 折扣率范围 1 - 100 默认 100(计算方式：85/100=0.85 即85折)，即不打折
     */
	private Integer discount;
    /**
     * 是否开启周末价 0 开启 1 禁用，即显示门市价即可
     */
	@TableField("weekend_fare_enable")
	private Integer weekendFareEnable;
    /**
     * 周末价格
     */
	@TableField("weekend_fare")
	private Integer weekendFare;
    /**
     * 是否启用 0 默认开启 1 关闭 2 删除标记
     */
	@TableField("mark_modified")
	private Integer markModified;
    /**
     * 是否开启早餐券 0 开启 1 关闭
     */
	@TableField("breakfast_enable")
	private Integer breakfastEnable;
    /**
     * 早餐数量
     */
	@TableField("breakfast_quantity")
	private Integer breakfastQuantity;
    /**
     * 创建者ID
     */
	@TableField("created_by")
	private Integer createdBy;
    /**
     * 创建时间
     */
	@TableField("created_at")
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	@TableField("updated_by")
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	@TableField("updated_at")
	private Date updatedAt;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TRoomCategory{" +
			"id=" + id +
			", name=" + name +
			", roomCount=" + roomCount +
			", hotelId=" + hotelId +
			", storeId=" + storeId +
			", desc=" + desc +
			", rackRate=" + rackRate +
			", deposit=" + deposit +
			", discountEnable=" + discountEnable +
			", discount=" + discount +
			", weekendFareEnable=" + weekendFareEnable +
			", weekendFare=" + weekendFare +
			", markModified=" + markModified +
			", breakfastEnable=" + breakfastEnable +
			", breakfastQuantity=" + breakfastQuantity +
			", createdBy=" + createdBy +
			", createdAt=" + createdAt +
			", updatedBy=" + updatedBy +
			", updatedAt=" + updatedAt +
			"}";
	}
=======
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

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
