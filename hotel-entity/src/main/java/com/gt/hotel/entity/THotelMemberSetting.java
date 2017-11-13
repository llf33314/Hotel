package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 酒店会员设置
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class THotelMemberSetting extends Model<THotelMemberSetting> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 酒店ID
     */
    private Integer hotelId;
    /**
     * 会员等级 目前只有4个等级 1,2,3,4
     */
    private Integer vipLevel;
    /**
     * 会员 最晚退房时间(需要默认退房时间均为离店当天14点)
     */
    private Date    vipCheckOut;
    /**
     * 是否免押金  0 是 1 否
     */
    private Integer freeDepositEnable;


    @Override
    protected Serializable pkVal() {
        return this.hotelId;
    }

}
