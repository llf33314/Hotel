package com.gt.hotel.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 房型
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TRoomCategory extends Model<TRoomCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 房型名称
     */
    private String  name;
    /**
     * 房间总数
     */
    private Integer roomCount;
    /**
     * 酒店ID
     */
    private Integer hotelId;
    /**
     * 描述
     */
    private String  desc;
    /**
     * 门市价
     */
    private Integer rackRate;
    /**
     * 押金
     */
    private Integer deposit;
    /**
     * 是否开启周末价 0 开启 1 禁用，即显示门市价即可
     */
    private Integer weekendFareEnable;
    /**
     * 周末价格
     */
    private Integer weekendFare;
    /**
     * 是否启用 0 默认开启 1 关闭 2 删除标记
     */
    @TableLogic
    private Integer markModified;
    /**
     * 是否开启早餐券 0 开启 1 关闭
     */
    private Integer breakfastEnable;
    /**
     * 早餐数量
     */
    private Integer breakfastQuantity;
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
