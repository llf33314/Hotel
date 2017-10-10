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
 * 酒店主表
 * </p>
 *
<<<<<<< HEAD
 * @author 
 * @since 2017-10-09
 */
@Data
@Accessors(chain = true)
@TableName("t_hotel")
public class THotel extends Model<THotel> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家ID 酒店所有者
     */
	@TableField("bus_id")
	private Integer busId;
    /**
     * 门店ID
     */
	@TableField("store_id")
	private Integer storeId;
    /**
     * 酒店名称
     */
	private String name;
    /**
     * 酒店描述		
     */
	private String desc;
    /**
     * 酒店负责人 手机号
     */
	private String phone;
    /**
     * 酒店地址		
     */
	private String address;
    /**
     * 酒店缩略图 URL 地址
     */
	private String thumbnail;
    /**
     * 酒店Logo
     */
	private String logo;
	private Double longitude;
	private Double latitude;
    /**
     * 标记备注 0 默认开启 1 关闭 2 删除标记
     */
	@TableField("mark_modified")
	private Integer markModified;
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

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getMarkModified() {
		return markModified;
	}

	public void setMarkModified(Integer markModified) {
		this.markModified = markModified;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "THotel{" +
			"id=" + id +
			", busId=" + busId +
			", storeId=" + storeId +
			", name=" + name +
			", desc=" + desc +
			", phone=" + phone +
			", address=" + address +
			", thumbnail=" + thumbnail +
			", logo=" + logo +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", markModified=" + markModified +
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
@TableName( "t_hotel" )
public class THotel extends Model< THotel > {

    private static final long serialVersionUID = 1L;

    @TableId( value = "id", type = IdType.AUTO )
    private Integer id;
    /**
     * 商家ID 酒店所有者
     */
    @TableField( "bus_id" )
    private Integer busId;
    /**
     * 门店ID
     */
    @TableField( "store_id" )
    private Integer storeId;
    /**
     * 酒店名称
     */
    private String  name;
    /**
     * 酒店描述
     */
    private String  desc;
    /**
     * 酒店负责人 手机号
     */
    private String  phone;
    /**
     * 酒店地址
     */
    private String  address;
    /**
     * 酒店缩略图 URL 地址
     */
    private String  thumbnail;
    /**
     * 酒店Logo
     */
    private String  logo;
    private Double  longitude;
    private Double  latitude;
    /**
     * 标记备注 0 默认开启 1 关闭 2 删除标记
     */
    @TableField( "mark_modified" )
    private Integer markModified;
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
