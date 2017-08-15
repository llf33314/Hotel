package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 酒店 - 基础设施
 * </p>
 *
 * @author 
 * @since 2017-08-08
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_installation_relation")
public class TErpHotelInstallationRelation extends Model<TErpHotelInstallationRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 酒店ID
     */
    @TableId("hotel_id")
	private Integer hotelId;
    /**
     * 基础设施ID
     */
	@TableField("installation_id")
	private Integer installationId;


	@Override
	protected Serializable pkVal() {
		return this.hotelId;
	}

	@Override
	public String toString() {
		return "TErpHotelInstallationRelation{" +
			"hotelId=" + hotelId +
			", installationId=" + installationId +
			"}";
	}
}
