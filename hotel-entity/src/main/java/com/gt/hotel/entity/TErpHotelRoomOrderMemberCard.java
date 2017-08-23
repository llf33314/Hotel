package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店-房间-订单-会员卡使用情况
 * </p>
 *
 * @author 
 * @since 2017-08-17
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_room_order_member_card")
public class TErpHotelRoomOrderMemberCard extends Model<TErpHotelRoomOrderMemberCard> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	@TableField("order_id")
	private Integer orderId;
    /**
     * 卡券code
     */
	private String cardVolumeCode;
    /**
     * 粉币
     */
	private Integer fenbi;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelRoomOrderMemberCard{" +
			"id=" + id +
			", orderId=" + orderId +
			", cardVolumeCode=" + cardVolumeCode +
			", fenbi=" + fenbi +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCardVolumeCode() {
		return cardVolumeCode;
	}

	public void setCardVolumeCode(String cardVolumeCode) {
		this.cardVolumeCode = cardVolumeCode;
	}

	public Integer getFenbi() {
		return fenbi;
	}

	public void setFenbi(Integer fenbi) {
		this.fenbi = fenbi;
	}
}
