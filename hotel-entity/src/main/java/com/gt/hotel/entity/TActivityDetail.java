package com.gt.hotel.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动详情表 根据活动类型不同整合各个活动所需字段
 * </p>
 *
 * @author 
 * @since 2017-11-18
 */
@Data
@Accessors(chain = true)
public class TActivityDetail extends Model<TActivityDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
	private Integer activityId;
    /**
     * 活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房
     */
	private Integer activityType;
    /**
     * 当activity=2 , 此项不能为空，入住时长。 单位分钟
     */
	private Integer checkInTime;
    /**
     * 如果 activity_type=2 钟点房 客人可入住时间段  始
     */
	private Date checkInPeriod;
    /**
     * 如果 activity_type=2 钟点房 客人可入住时间段 末
     */
	private Date checkOutPeriod;
    /**
     * 如果 activity=4 团购房 规则 最少预订房间数量
     */
	private Integer minPurchaseNumber;
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
		return this.activityId;
	}

}
