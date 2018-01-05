package com.gt.hotel.param.erp;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gt.hotel.param.HotelPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Api("协议单位 or 中介 参数")
public class AgreementParamter {
	
	@Api(description = "协议单位 or 中介 列表 参数")
    @Data
    @EqualsAndHashCode(callSuper=false)
    public static class AgreementQuery extends HotelPage {
        @ApiModelProperty(value = "组织ID")
        private Integer id;

        @ApiModelProperty("审核状态 字典类型 7 三种状态 0 待审核 1 已通过 2 不通过 (审核人必须是财务角色)")
    	private Integer status;
        
        @ApiModelProperty("busId")
        private Integer busId;

        @ApiModelProperty("模块类型： 0 单位 1 中介")
        @NotNull(message = "模块类型不能为空")
        private Integer module;
    }

	@Api(description = "收款/财务 列表 参数")
	@Data
	public static class ReceivablesQuery extends HotelPage {
		
		@ApiModelProperty("审核状态 字典类型 7 三种状态 0 待审核 1 已通过 2 不通过 (审核人必须是财务角色)")
		private Integer status;
		
		@ApiModelProperty("入住时间段 开始")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomInTime;
		
		@ApiModelProperty("入住时间段 结束")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date roomOutTime;
		
		@ApiModelProperty("模块类型： 0 单位 1 中介")
		private Integer module;
	}
	
	@Api(description = "协议单位 or 中介 列表 新增参数")
    @Data
    public static class AgreementInsert {
		private Integer id;

		@ApiModelProperty(value = "酒店ID")
		private Integer hotelId;

		@ApiModelProperty(value = "名称")
		private String name;

		@ApiModelProperty(value = "联系人")
		private String contact;

		@ApiModelProperty(value = "手机号码")
		private String phone;

		@ApiModelProperty(value = "固定电话")
		private String tel;

		@ApiModelProperty(value = "合同号")
		private String contractNum;
		
		@ApiModelProperty(value = "备注")
		private String remark;

		@ApiModelProperty(value = "模块类型： 0 单位 1 中介")
		private Integer module;

		@ApiModelProperty(value = "套餐ID")
		private Integer packageId;
		
		@ApiModelProperty(value = "审核状态 字典类型 7 三种状态 0 待审核 1 已通过 2 不通过 (审核人必须是财务角色)")
		private Integer status;

		@ApiModelProperty(value = "驳回原因 ")
		private String rejectedReason;

    }
	
}
