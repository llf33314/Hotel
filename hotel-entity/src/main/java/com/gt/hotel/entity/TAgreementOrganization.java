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
 * 针对协议组织(单位/中介 使用哪种方式的套餐
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Data
@Accessors(chain = true)
public class TAgreementOrganization extends Model<TAgreementOrganization> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private Integer hotelId;
    /**
     * 名称
     */
	private String name;
    /**
     * 联系人
     */
	private String contact;
    /**
     *  手机号码
     */
	private String phone;
    /**
     * 固定电话
     */
	private String tel;
    /**
     * 合同号
     */
	private String contractNum;
	private String remark;
    /**
     * 模块类型： 0 单位 1 中介
     */
	private Integer module;
    /**
     * 套餐ID
     */
	private Integer packageId;
    /**
     * 审核状态 字典类型 7 三种状态 0 待审核 1 已通过 2 不通过 (审核人必须是财务角色)
     */
	private Integer status;
    /**
     * 驳回原因 
     */
	private String rejectedReason;
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
