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
 * 系统操作日志
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class SysOperationLog extends Model<SysOperationLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * module =HOTEL 则hotel_id =ROOM 则 room_id =ROOM_CATEGORY 则 房型ID =ORDER 则 order_id 等操作日志
     */
    private Integer referenceId;
    private String  module;
    /**
     * 动作 module =ORDER 下的 改价功能 例如 客房状态需要记录
     */
    private String  action;
    /**
     * 修改详情： 例如 订单下的改价功能 修改 原价格 288 -> 188 价格
     */
    private String  modifyDetails;
    /**
     * ip 地址
     */
    private String  ipAddress;
    /**
     * 处理结果 0 成功 1 失败
     */
    private Integer result;
    /**
     * 操作用户
     */
    private Integer operationId;
    /**
     * 操作用户名
     */
    private String  operatinName;
    /**
     * 创建时间
     */
    private Date    createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
