package com.gt.hotel.param;

import com.gt.hotel.vo.HotelMemberSettingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 房间 请求对象
 *
 * @author ReverieNight@Foxmail.com
 */

@Api(tags = "ERP设置 实体参数")
public class ERPParameter {

    @Data
    @Api("保存 参数")
    public static class ERPSave {
        @ApiModelProperty(value = "酒店ID", required = true)
        @NotNull(message = "酒店ID不能为空")
        private Integer hotelId;

        @ApiModelProperty(value = "LOGO URL", required = true)
        @NotNull(message = "LOGO URL 不能为空")
        private String logo;

        @ApiModelProperty("ERP 会员设置 集合")
        private List<HotelMemberSettingVo> memberSetting;
    }

    @Data
    @Api("保存 参数")
    public static class AuthorSave {

        @ApiModelProperty("账号ID")
        @NotNull(message = "账号ID不能为空")
        private Integer accountId;

        @ApiModelProperty("酒店ID")
        @NotNull(message = "酒店ID不能为空")
        private Integer hotelId;

        @ApiModelProperty("门店ID")
        @NotNull(message = "门店ID不能为空")
        private Integer shopId;

        @ApiModelProperty("功能ID 复数 存储方式：1,2,3,4 代表功能 =1 体现 =2 免押金 =3 修改房价 =4 挂账 ")
        @NotNull(message = "功能ID不能为空")
        private String functionIds;
    }

    @Data
    @Api("授权查询参数")
    public static class AuthorQuery extends HotelPage{
    	@ApiModelProperty(value = "门店ID", required = true)
        @NotNull(message = "门店ID不能为空")
        private Integer shopId;
    }
}

