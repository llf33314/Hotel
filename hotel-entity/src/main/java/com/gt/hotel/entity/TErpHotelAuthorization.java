package com.gt.hotel.entity;

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

/**
 * <p>
 * 授权人
 * </p>
 *
 * @author 
 * @since 2017-08-09
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
     * 核销员名称
     */
	@TableField("authorization_name")
	private String authorizationName;
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
			", shopId=" + shopId +
			", accountId=" + accountId +
			", authorizationName=" + authorizationName +
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

	public String getAuthorizationName() {
		return authorizationName;
	}

	public void setAuthorizationName(String authorizationName) {
		this.authorizationName = authorizationName;
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
