package com.gt.hotel.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登记入住客户 与 订单记录 一间客房 1 : n 入住客户身份信息(类似客户入住历史记录)
 * </p>
 *
 * @author 
 * @since 2017-10-30
 */
@Data
@Accessors(chain = true)
public class TOrderRoomCustomer extends Model<TOrderRoomCustomer> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 入住客户姓名
     */
	private String name;
    /**
     * 客户证件类型
     */
	private Integer idType;
    /**
     * 手机号码
     */
	private String phone;
    /**
     * 证件号
     */
	private String idCard;
    /**
     * 扫描证件照
     */
	private String idCardImage;
    /**
     * 订单ID
     */
	private Integer orderId;
    /**
     * 房号
     */
	private String roomNum;
    /**
     * 标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2
     */
	private Integer markModified;
    /**
     * 创建者ID
     */
	private Integer createdBy;
    /**
     * 创建时间
     */
	private Date createdAt;
    /**
     * 最后修改人 ID
     */
	private Integer updatedBy;
    /**
     * 最后修改时间
     */
	private Date updatedAt;
    /**
     * 性别(0:男, 1:女)
     */
	private Integer customerGender;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
