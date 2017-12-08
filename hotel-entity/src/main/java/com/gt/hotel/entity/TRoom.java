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
 * 房型关联 1 - n 客房信息
 * 房型的客房数量 对应 客房数
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TRoom extends Model<TRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 客房ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 房型ID t_room_category.id
     */
    private Integer categoryId;
    /**
     * 客房编号
     */
    private String  number;
    /**
     * 楼层
     */
    private String  floor;
    /**
     * 房间状态 字典值 TODO: 后续给出编号  -- 可定义为字典值：在住、空房、脏房、已预订、已锁定、维护   sys_dictionary.dict_type_id=6
     */
    private Integer  status;
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
