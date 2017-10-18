package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 预订客户
 * </p>
 *
 * @author 
 * @since 2017-10-18
 */
@Data
@Accessors(chain = true)
public class TReservationConsumer extends Model<TReservationConsumer> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 客户姓名
     */
	private String name;
    /**
     * 证件类型
     */
	private String idType;
    /**
     * 证件编号
     */
	private String idNumber;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
