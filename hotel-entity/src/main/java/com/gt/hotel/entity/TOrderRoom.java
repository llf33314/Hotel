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
 * 酒店订单 客房订单 (客单)
 * </p>
 *
 * @author 
 * @since 2017-10-30
 */
@Data
@Accessors(chain = true)
public class TOrderRoom extends Model<TOrderRoom> {

    private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

    /**
     * 订单ID
     */
	private Integer orderId;
    /**
     * 订单编号 来自 t_order.order_num
     */
	private String orderNum;
    /**
     * 酒店ID
     */
	private Integer hotelId;
    /**
     * 冗余参数 酒店名称
     */
	private String hotelName;
    /**
     * 房型ID
     */
	private Integer categoryId;
    /**
     * 冗余参数 房型名称
     */
	private String categoryName;
    /**
     * 入住方式 0 预订订单(预约订单: 网上|电话|移动端 预订过产生了一条预订消息则为预订订单)  1 到店入住(直接办理入住)
     */
	private Integer checkInWay;
    /**
     * 房号 来自 t_room.number 预约订单则不会自动锁定房号
     */
	private String roomNum;
    /**
     * 预订客户姓名
     */
	private String customerName;
    /**
     * 证件类型 =0 二代身份证 =1 一代身份证 =2 驾驶证 =3 护照 =4 军官证 =5 士兵证 =6 港澳通行证 =7 其他
     */
	private Integer customerIdType;
    /**
     * 证件号
     */
	private String customerIdCard;
    /**
     * 证件照 图片地址
     */
	private String customerIdCardImage;
    /**
     * 预订客户 手机号码
     */
	private String customerPhone;
    /**
     * 客房数量 默认1
     */
	private Integer number;
    /**
     * 抵店时间
     */
	private Date roomInTime;
    /**
     * 离店时间
     */
	private Date roomOutTime;
    /**
     * 当前门市价 固定从房型获取(周一至周四为 门市价 周五周六 为周末价。如果有日历价，则为日历价)
     */
	private Integer rackRate;
    /**
     * 应收( (客房价格+押金) * 数量 room_price+deposit ) 
     */
	private Integer receivablePrice;
    /**
     * 押金 来自 t_room_catrgory.deposit 0 则为免押金
     */
	private Integer deposit;
    /**
     * 当前客房价格 可以是改价后的价格
     */
	private Integer roomPrice;
    /**
     * 来源 1 后台(线下订单) 2 ERP 3 移动端(H5) 4 小程序 ，默认 0 未知来源  
     */
	private Integer orderFrom;
    /**
     * 支付时间
     */
	private Date payTime;
    /**
     * 支付状态 0 已支付 1 未支付
     */
	private Integer payStatus;
    /**
     * 活动ID，如果是活动进来的订单即可以使用活动ID
     */
	private Integer activityId;
    /**
     * 订单(退房)完成时间
     */
	private Date completionTime;
    /**
     * 创建订单时间
     */
	private Date createTime;
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
     * 住客类型(0:散客/会员, 1:协议单位)
     */
	private Integer guestType;
    /**
     * 性别(0:男, 1:女)
     */
	private Integer customerGender;
	/**
	 * 入住标准 0 全天房 1 特价房 2 钟点房 3 秒杀房 4 团购房
	 */
	private Integer checkStandard;
	/**
	 * 退房说明
	 */
	private String checkOutInstructions;
	/**
	 * 押金可见状态 0 true 1 false
	 */
	private Integer depositVisible;
	/**
	 * 钟点房入住时间 HH:mm:ss
	 */
	private Date hourRoomCheckInTime;


	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

}
