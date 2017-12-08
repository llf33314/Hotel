package com.gt.hotel.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 针对协议组织(单位/中介 使用哪种方式的套餐
 * @author Reverien9@gmail.com
 * 2017年11月24日 下午5:47:35
 */
@Api("针对协议组织(单位/中介 使用哪种方式的套餐")
@Data
public class AgreementOrganizationVo {

	private Integer id;

	@ApiModelProperty("酒店ID")
	private Integer hotelId;
	
	@ApiModelProperty("酒店名称")
	private String hotelName;

	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty("联系人")
	private String contact;

	@ApiModelProperty("手机号码")
	private String phone;

	@ApiModelProperty("固定电话")
	private String tel;

	@ApiModelProperty("合同号")
	private String contractNum;
	
	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("模块类型： 0 单位 1 中介")
	private Integer module;

	@ApiModelProperty("套餐ID")
	private Integer packageId;
	
	@ApiModelProperty("套餐名称")
	private String packageName;

	@ApiModelProperty("审核状态 字典类型 7 三种状态 0 待审核 1 已通过 2 不通过 (审核人必须是财务角色)")
	private Integer status;

	@ApiModelProperty("驳回原因 ")
	private String rejectedReason;

	@ApiModelProperty("标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2")
	private Integer markModified;

	@ApiModelProperty("创建者ID")
	private Integer createdBy;

	@ApiModelProperty("创建时间")
	private Date createdAt;

	@ApiModelProperty("最后修改人 ID")
	private Integer updatedBy;

	@ApiModelProperty("最后修改时间")
	private Date updatedAt;

	private List<PackageRoomVo> packageRoomVos;
}
