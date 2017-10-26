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
 * 活动设置 - 抽出活动公共设置
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TActivity extends Model<TActivity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 酒店ID
     */
    private Integer hotelId;
    /**
     * 活动编号 提供对外显示 ID 格式 前缀 HD+时间戳年月日时分秒毫秒 14位
     */
    private String  activityNum;
    /**
     * 活动 开始时间
     */
    private Date    beginTime;
    /**
     * 结束时间
     */
    private Date    endTime;
    /**
     * 可入住最大时间 活动开始后，预订入住时间 最大有效期。
     */
    private Date    availableTime;
    /**
     * 是否开启限购 0 — 开启  1 不限制  默认1
     */
    private Integer restrictionEnable;
    /**
     * 限购数量 当限购开启 - 限购最少限购1间
     */
    private Integer restrictionNumber;
    /**
     * 是否开启显示房间数 0 是  1 不是 默认 1
     */
    private Integer showRoomEnable;
    /**
     * 显示剩余房间数的临界值 当show_room_enable=0 此值不能为空。最大999
     */
    private Integer numberThreshold;
    /**
     * 活动规则
     */
    private String  activityRules;
    /**
     * 活动发布状态 0 开启 1 未开启
     */
    private Integer publishStatus;
    /**
     * 0 正常 1 禁用 2 删除
     */
    private Integer markModified;
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
    private Date    updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
