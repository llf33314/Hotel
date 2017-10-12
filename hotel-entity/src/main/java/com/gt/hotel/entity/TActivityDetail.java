package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活动详情表 根据活动类型不同整合各个活动所需字段
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Data
@Accessors( chain = true )
@TableName( "t_activity_detail" )
public class TActivityDetail extends Model< TActivityDetail > {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId( "activity_id" )
    private Integer activityId;
    /**
     * 活动类型 1 特价房 2 钟点房 3 秒杀房 4 团购房
     */
    @TableField( "activity_type" )
    private Integer activityType;
    /**
     * 当activity=2 , 此项不能为空，入住时长。 单位分钟
     */
    @TableField( "check_in_time" )
    private Integer checkInTime;
    /**
     * 如果 activity_type=2 钟点房 客人可入住时间段
     */
    @TableField( "check_in_period" )
    private Date    checkInPeriod;
    /**
     * 如果 activity=4 团购房 规则 最少预订房间数量
     */
    @TableField( "min_purchase_number" )
    private Integer minPurchaseNumber;
    /**
     * 创建者ID
     */
    @TableField( "created_by" )
    private Integer createdBy;
    /**
     * 创建时间
     */
    @TableField( "created_at" )
    private Date    createdAt;
    /**
     * 最后修改人 ID
     */
    @TableField( "updated_by" )
    private Integer updatedBy;
    /**
     * 最后修改时间
     */
    @TableField( "updated_at" )
    private Date    updatedAt;

    @Override
    protected Serializable pkVal() {
	return this.activityId;
    }
}
