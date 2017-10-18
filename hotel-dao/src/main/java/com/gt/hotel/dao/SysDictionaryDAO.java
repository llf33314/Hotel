package com.gt.hotel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.SysDictionary;
import com.gt.hotel.vo.SysDictionaryVo;

/**
 * <p>
 * 系统的字典数据表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface SysDictionaryDAO extends BaseMapper< SysDictionary > {

	/**
	 * 查询 发票列表
	 * @param page
	 * @return
	 */
	List<SysDictionaryVo> queryInvoice(@Param("dictName") String dictName, @Param("page") Pagination page);

}