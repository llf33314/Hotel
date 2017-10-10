package com.gt.hotel.responseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
<<<<<<< HEAD

/**
 * 新增酒店 - 门店列表
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午2:33:58
 */
@Api(description = "新增酒店 - 门店列表")
public class HotelShopInfo {
	
	@ApiModelProperty("门店ID")
	private Integer shopid;
	@ApiModelProperty("门店名称")
	private String name;
	@ApiModelProperty("联系电话")
	private String tel;
	@ApiModelProperty("地址")
	private String addr;
	@ApiModelProperty("图片地址")
	private String image;
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "HotelShopInfo [shopid=" + shopid + ", name=" + name + ", tel=" + tel + ", addr=" + addr + ", image="
				+ image + "]";
	}
	
	
=======
import lombok.Data;

/**
 * 新增酒店 - 门店列表
 *
 * @author ReverieNight@Foxmail.com
 * @date 2017年10月9日 下午2:33:58
 */
@Data
@Api( description = "新增酒店 - 门店列表" )
public class HotelShopInfo {

    @ApiModelProperty( "门店ID" )
    private Integer shopid;
    @ApiModelProperty( "门店名称" )
    private String  name;
    @ApiModelProperty( "联系电话" )
    private String  tel;
    @ApiModelProperty( "地址" )
    private String  addr;
    @ApiModelProperty( "图片地址" )
    private String  image;

>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
}
