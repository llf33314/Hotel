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
 * 客房长包房 长包房押金规则： 如 入住3天 押金按长包房押金收
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TRoomPermanent extends Model<TRoomPermanent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    /**
     * 酒店ID t_hotel.id
     */
    private Integer hotelId;
    /**
     * 规则内容
     */
    private String  ruleName;
    /**
     * 入住天数
     */
    private Integer checkInDay;
    /**
     * 折扣 默认：1 无折扣(门市价/日历价 * 折扣) = 长包房当天价格
     */
    private Integer discount;
    /**
     * 押金
     */
    private Integer deposit;
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
