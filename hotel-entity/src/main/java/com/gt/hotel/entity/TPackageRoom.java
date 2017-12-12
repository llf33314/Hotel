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
 * 套餐内的房型与价格
 * </p>
 *
 * @author 
 * @since 2017-11-24
 */
@Data
@Accessors(chain = true)
public class TPackageRoom extends Model<TPackageRoom> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 协议套餐 ID
     */
	private Integer packageId;
    /**
     * 房型ID
     */
	private Integer categoryId;
    /**
     * 房型名称
     */
	private String categoryName;
    /**
     * 门市价
     */
	private Integer rackRate;
    /**
     * 折扣 百分比 整数
     */
	private Integer discount;
    /**
     * 协议价=门市价 * 折扣 
     */
	private Integer agreementPrice;
    /**
     * 标记备注 0 启用 1 禁用 2 删除 默认0  可查看 sys_dictionary.dict_type_id=2
     */
	@TableLogic
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
