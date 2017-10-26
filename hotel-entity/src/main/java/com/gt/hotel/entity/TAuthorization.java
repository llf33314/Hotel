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
 * ERP 功能授权管理
 * </p>
 *
 * @author zhangmz
 * @since 2017-10-26
 */
@Data
@Accessors(chain = true)
public class TAuthorization extends Model<TAuthorization> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号ID
     */
    private Integer accountId;
    /**
     * 酒店ID
     */
    private Integer hotelId;
    /**
     * 功能ID 复数 存储方式：1,2,3,4 代表功能 =1 体现 =2 免押金 =3 修改房价 =4 挂账 来自 sys_dictionary.dict_type_id=3
     */
    private String  functionIds;
    /**
     * 是否已扫码授权 0 是 1 否
     */
    private Integer scanCodeAuthorization;
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
