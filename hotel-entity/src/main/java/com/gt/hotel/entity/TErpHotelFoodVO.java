package com.gt.hotel.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * ERP酒店菜品
 * </p>
 *
 * @author 
 * @since 2017-08-16
 */
@ApiModel("TErpHotelFoodVO")
@Data
public class TErpHotelFoodVO extends Model<TErpHotelFoodVO> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @ApiModelProperty("菜品对象")
    private TErpHotelFood food;

    @ApiModelProperty("图片集合")
	private List<TErpHotelImage> images;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TErpHotelFood getFood() {
		return food;
	}

	public void setFood(TErpHotelFood food) {
		this.food = food;
	}

	public List<TErpHotelImage> getImages() {
		return images;
	}

	public void setImages(List<TErpHotelImage> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "TErpHotelFoodVO [id=" + id + ", food=" + food + ", images=" + images + "]";
	}

}
