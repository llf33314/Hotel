/**
  * Copyright 2017 bejson.com 
  */
package com.gt.hotel.other;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员卡
 * @author Reverien9@gmail.com
 * 2017年11月14日 上午11:37:03
 */
@Api("会员卡")
@Data
public class MemberCard {

	@ApiModelProperty("多粉卡券")
    private List<DuofenCards> duofenCards;
    
    @ApiModelProperty("等级")
    private String gradeName;
    
    @ApiModelProperty("积分抵扣下限金额")
    private int jifenStartMoney;
    
    @ApiModelProperty("会员类型id")
    private int ctId;
    
    @ApiModelProperty("粉币抵扣下限金额")
    private int fenbiStartMoney;
    
    @ApiModelProperty("昵称")
    private String nickName;
    
    @ApiModelProperty("粉币值")
    private int fans_currency;
    
    @ApiModelProperty("折扣值")
    private double discount;
    
    @ApiModelProperty("会员卡类型")
    private String ctName;
    
    private int getFenbiMoeny;
    
    @ApiModelProperty("会员卡号")
    private String cardNo;
    
    @ApiModelProperty("剩余次数")
    private int frequency;
    
    @ApiModelProperty("粉币兑换率")
    private double fenbiRatio;
    
    @ApiModelProperty("积分最高抵扣金额")
    private int jifenMoeny;
    
    @ApiModelProperty("剩余金额")
    private int money;
    
    @ApiModelProperty("手机号码")
    private String phone;
    
    @ApiModelProperty("积分兑换率")
    private int jifenRatio;
    
    @ApiModelProperty("积分值")
    private int integral;
    
    @ApiModelProperty("会员卡id")
    private int cardId;
    
    private int getJifenMoeny;
    
    @ApiModelProperty("粉币最高抵扣金额")
    private int fenbiMoeny;
    
    @ApiModelProperty("粉丝id")
    private int memberId;

    @ApiModelProperty("memberDate=1会员日")
    private int memberDate;
    
    @ApiModelProperty("会员日折扣")
    private double memberDiscount;
}
