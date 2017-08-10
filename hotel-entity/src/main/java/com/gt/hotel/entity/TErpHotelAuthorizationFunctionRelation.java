package com.gt.hotel.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 授权功能
 * </p>
 *
 * @author 
 * @since 2017-08-09
 */
@Data
@Accessors(chain = true)
@TableName("t_erp_hotel_authorization_function_relation")
public class TErpHotelAuthorizationFunctionRelation extends Model<TErpHotelAuthorizationFunctionRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 授权人ID
     */
    @TableId("author_id")
	private Integer authorId;
    /**
     * 功能ID
     */
	@TableField("function_id")
	private Integer functionId;


	@Override
	protected Serializable pkVal() {
		return this.authorId;
	}

	@Override
	public String toString() {
		return "TErpHotelAuthorizationFunctionRelation{" +
			"authorId=" + authorId +
			", functionId=" + functionId +
			"}";
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	
	
}
