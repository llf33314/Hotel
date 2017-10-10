package com.gt.hotel.entity;

<<<<<<< HEAD
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
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
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
/**
 * <p>
 * 酒店移动端设置
 * </p>
 *
<<<<<<< HEAD
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_hotel_setting")
public class THotelSetting extends Model<THotelSetting> {
=======
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_hotel_setting" )
public class THotelSetting extends Model< THotelSetting > {
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6

    private static final long serialVersionUID = 1L;

    /**
     * 酒店ID
     */
<<<<<<< HEAD
    @TableId("hotel_id")
	private Integer hotelId;
    /**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付
     */
	@TableField("pay_mode")
	private Integer payMode;
    /**
     * 是否开启短信通知 默认 1 不开启 0 开启
     */
	@TableField("sms_enable")
	private Integer smsEnable;
    /**
     * 接收短信的手机号
     */
	@TableField("sms_phone")
	private String smsPhone;
    /**
     * 酒店公告 1 不开启 0 开启
     */
	@TableField("notice_enable")
	private Integer noticeEnable;
    /**
     * 公告
     */
	private String bulletin;
    /**
     * 是否显示剩余房间数 默认 1 关闭 0 开启
     */
	@TableField("remnant_room_enable")
	private Integer remnantRoomEnable;
    /**
     * 餐饮支付方式 1 在线 2 到付 3 在线&到付
     */
	@TableField("food_pay_mode")
	private Integer foodPayMode;
    /**
     * 预约退房 0 开启 1 关闭  默认1
     */
	@TableField("reservation_check_out_enable")
	private Integer reservationCheckOutEnable;
    /**
     * 预约退房 接收短信的手机号
     */
	@TableField("reservation_check_out_phone")
	private String reservationCheckOutPhone;
    /**
     * 发票支持的类目 1 办公用品 2 住宿费 3 餐费 4 培训费 5 打球费 6 健身费 存储方式 1,2,3,4,5 or 1,2,3
     */
	@TableField("Invoice_category")
	private String InvoiceCategory;
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
		return this.hotelId;
	}

	@Override
	public String toString() {
		return "THotelSetting{" +
			"hotelId=" + hotelId +
			", payMode=" + payMode +
			", smsEnable=" + smsEnable +
			", smsPhone=" + smsPhone +
			", noticeEnable=" + noticeEnable +
			", bulletin=" + bulletin +
			", remnantRoomEnable=" + remnantRoomEnable +
			", foodPayMode=" + foodPayMode +
			", reservationCheckOutEnable=" + reservationCheckOutEnable +
			", reservationCheckOutPhone=" + reservationCheckOutPhone +
			", InvoiceCategory=" + InvoiceCategory +
			", createdBy=" + createdBy +
			", createdAt=" + createdAt +
			", updatedBy=" + updatedBy +
			", updatedAt=" + updatedAt +
			"}";
	}
=======
    @TableId( "hotel_id" )
    private Integer hotelId;
    /**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付
     */
    @TableField( "pay_mode" )
    private Integer payMode;
    /**
     * 是否开启短信通知 默认 1 不开启 0 开启
     */
    @TableField( "sms_enable" )
    private Integer smsEnable;
    /**
     * 接收短信的手机号
     */
    @TableField( "sms_phone" )
    private String  smsPhone;
    /**
     * 酒店公告 1 不开启 0 开启
     */
    @TableField( "notice_enable" )
    private Integer noticeEnable;
    /**
     * 公告
     */
    private String  bulletin;
    /**
     * 是否显示剩余房间数 默认 1 关闭 0 开启
     */
    @TableField( "remnant_room_enable" )
    private Integer remnantRoomEnable;
    /**
     * 餐饮支付方式 1 在线 2 到付 3 在线&到付
     */
    @TableField( "food_pay_mode" )
    private Integer foodPayMode;
    /**
     * 预约退房 0 开启 1 关闭  默认1
     */
    @TableField( "reservation_check_out_enable" )
    private Integer reservationCheckOutEnable;
    /**
     * 预约退房 接收短信的手机号
     */
    @TableField( "reservation_check_out_phone" )
    private String  reservationCheckOutPhone;
    /**
     * 发票支持的类目 1 办公用品 2 住宿费 3 餐费 4 培训费 5 打球费 6 健身费 存储方式 1,2,3,4,5 or 1,2,3
     */
    @TableField( "Invoice_category" )
    private String  InvoiceCategory;
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
        return this.hotelId;
    }

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
