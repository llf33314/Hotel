package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 授权人-功能-关系
 * </p>
 *
 * @author 
 * @since 2017-08-11
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_authorization")
public class TErpHotelAuthorization extends Model<TErpHotelAuthorization> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 酒店ID
     */
	@TableField("hotel_id")
	private Integer hotelId;
    /**
     * 门店ID
     */
	@TableField("shop_id")
	private Integer shopId;
    /**
     * 账号ID
     */
	@TableField("account_id")
	private Integer accountId;
    /**
     * 功能ID
     */
	@TableField("function_id")
	private Integer functionId;
    /**
     * 创建者
     */
	private String creator;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelAuthorization{" +
			"id=" + id +
			", hotelId=" + hotelId +
			", shopId=" + shopId +
			", accountId=" + accountId +
			", functionId=" + functionId +
			", creator=" + creator +
			", createTime=" + createTime +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
