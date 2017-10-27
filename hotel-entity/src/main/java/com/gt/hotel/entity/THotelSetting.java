package com.gt.hotel.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 酒店移动端设置
 * </p>
 *
 * @author 
 * @since 2017-10-27
 */
@Data
@Accessors(chain = true)
public class THotelSetting extends Model<THotelSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 酒店ID
     */
    private Integer hotelId;
    /**
     * 支付方式(1：在线支付 | 2：到店支付 | 3：1&2)  默认 在线支付
     */
    private Integer payMode;
    /**
     * 是否开启短信通知 默认 1 不开启 0 开启
     */
    private Integer smsEnable;
    /**
     * 接收短信的手机号
     */
    private String  smsPhone;
    /**
     * 酒店公告 1 不开启 0 开启
     */
    private Integer noticeEnable;
    /**
     * 公告内容
     */
    private String  bulletin;
    /**
     * 是否显示剩余房间数 默认 1 关闭 0 开启
     */
    private Integer remnantRoomEnable;
    /**
     * 开启客房订餐 0 开启  1 不开启 默认1
     */
    private Integer roomReservationEnable;
    /**
     * 餐饮支付方式 1 在线 2 到付 3 在线&到付
     */
    private Integer foodPayMode;
    /**
     * 预约退房 0 开启 1 关闭  默认1
     */
    private Integer reservationCheckOutEnable;
    /**
     * 预约退房 接收短信的手机号
     */
    private String  reservationCheckOutPhone;
    /**
     * 发票支持的类目 1 办公用品 2 住宿费 3 餐费 4 培训费 5 打球费 6 健身费 存储方式 1,2,3,4,5 or 1,2,3 来自sys_dictionary.dict_type_id = 4
     */
	private String invoiceCategorys;
    /**
     * 创建者ID
     */
    private Integer createdBy;
    /**
     * 创建时间
     */
    private Date    createdAt;
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
        return this.hotelId;
    }

}
