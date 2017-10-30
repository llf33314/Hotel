package com.gt.hotel.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ERP 功能授权管理
 *
 * @author Administrator
 */
@Data
@Api(description = "ERP 功能授权管理")
public class AuthorizationVo {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("账号ID")
    private Integer accountId;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("门店ID")
    private Integer shopId;

    @ApiModelProperty("功能ID 复数 存储方式：1,2,3,4 代表功能 =1 体现 =2 免押金 =3 修改房价 =4 挂账 来自 sys_dictionary.dict_type_id=3")
    private String functionIds;

    @ApiModelProperty("标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2")
    private Integer markModified;

    @ApiModelProperty(value = "创建者ID")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "最后修改人 ID")
    private Integer updatedBy;

    @ApiModelProperty(value = "最后修改时间")
    private Date updatedAt;

    @ApiModelProperty("酒店ID")
    private String hotelName;

    @ApiModelProperty("授权功能")
    private String functions;

    @ApiModelProperty("是否已扫码授权 0 是 1 否")
	private Integer scanCodeAuthorization;
    
    @ApiModelProperty("姓名")
    private String name;
    
    @ApiModelProperty("手机")
    private String phone;
}
