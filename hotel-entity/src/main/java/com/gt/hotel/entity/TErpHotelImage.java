package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ERP酒店图片
 * </p>
 *
 * @author 
 * @since 2017-08-08
 */
@ApiModel("TErpHotelImage")
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_image")
public class TErpHotelImage extends Model<TErpHotelImage> {

    private static final long serialVersionUID = 1L;

    /**
     * 隶属(0:酒店
     */
    public static final Integer HOTEL = 0;
    /**
     * 隶属(1:房型
     */
    public static final Integer ROOM = 1;
    /**
     * 隶属(2:菜品
     */
    public static final Integer FOOD = 2;
    
    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 隶属ID
     */
	@ApiModelProperty("隶属ID")
	@TableField("subjection_id")
	private Integer subjectionId;
    /**
     * 名称
     */
	@ApiModelProperty("名称")
	private String name;
    /**
     * 路径
     */
	@ApiModelProperty("路径")
	private String url;
    /**
     * 类型
     */
	@ApiModelProperty("类型")
	private String type;
    /**
     * 隶属(0:酒店, 1:房型, 2:菜品, 等等)
     */
	@ApiModelProperty("隶属(0:酒店, 1:房型, 2:菜品, 等等)")
	private Integer subjection;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TErpHotelImage{" +
			"id=" + id +
			", subjectionId=" + subjectionId +
			", name=" + name +
			", url=" + url +
			", type=" + type +
			", subjection=" + subjection +
			"}";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSubjectionId() {
		return subjectionId;
	}

	public void setSubjectionId(Integer subjectionId) {
		this.subjectionId = subjectionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSubjection() {
		return subjection;
	}

	public void setSubjection(Integer subjection) {
		this.subjection = subjection;
	}
	
	
}