package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gt.hotel.entity.TInfrastructureRelation;
import com.gt.hotel.vo.InfrastructureRelationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 基础设施表关系(酒店/房型 关联的基础属性都在这里) Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-11
 */
public interface TInfrastructureRelationDAO extends BaseMapper<TInfrastructureRelation> {

    /**
     * 查找 基础设施列表
     * @param refId 引用ID
     * @param module 模块类型
     * @return List
     */
    List<InfrastructureRelationVo> findRelationVoListByRefIdAndModule(@Param("refId")Integer refId,@Param("module")String module);

}