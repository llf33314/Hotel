package com.gt.hotel.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.dao.TErpHotelFoodDAO;
import com.gt.hotel.entity.TErpHotelFood;
import com.gt.hotel.entity.TErpHotelFoodVO;
import com.gt.hotel.entity.TErpHotelImage;
import com.gt.hotel.web.service.TErpHotelFoodService;
import com.gt.hotel.web.service.TErpHotelImageService;

/**
 * <p>
 * ERP酒店菜品 服务实现类
 * </p>
 *
 * @author 
 * @since 2017-08-16
 */
@Service
public class TErpHotelFoodServiceImpl extends BaseServiceImpl<TErpHotelFoodDAO, TErpHotelFood> implements TErpHotelFoodService {
	
	@Autowired
	TErpHotelImageService TErpHotelImageService;

	@Transactional
	@Override
	public boolean insertOrUpdate(TErpHotelFood food, List<TErpHotelImage> images) {
		boolean flag = false;
		if(food.getId() != null){
			if(images.size() > 0){
				Wrapper<TErpHotelImage> iw = new EntityWrapper<TErpHotelImage>();
				iw.eq("subjection_id", food.getId());
				iw.eq("subjection", 2);
				iw.eq("type", "菜品图片");
				TErpHotelImageService.delete(iw);
			}
		}
		this.insertOrUpdate(food);
		for(TErpHotelImage i : images){
			i.setSubjectionId(food.getId());
			i.setSubjection(2);
			i.setType("菜品图片");
		}
		TErpHotelImageService.insertBatch(images);
		flag = true;
		return flag;
	}
	
	@Transactional
	@Override
	public boolean deleteBatchIdsANDImage(List<Integer> idList) {
		boolean flag = false;
		this.deleteBatchIds(idList);
		Wrapper<TErpHotelImage> iw = new EntityWrapper<TErpHotelImage>();
		iw.in("subjection_id", idList);
		iw.eq("subjection", 2);
		iw.eq("type", "菜品图片");
		TErpHotelImageService.delete(iw);
		flag = true;
		return flag;
	}

	@Override
	public TErpHotelFoodVO selectPageFoodVO(Integer id) {
		TErpHotelFoodVO f = new TErpHotelFoodVO();
		TErpHotelFood _f = this.selectById(id);
		Wrapper<TErpHotelImage> iw = new EntityWrapper<TErpHotelImage>();
		iw.eq("subjection_id", id);
		iw.eq("subjection", 2);
		iw.eq("type", "菜品图片");
		List<TErpHotelImage> is = TErpHotelImageService.selectList(iw);
		f.setFood(_f);
		f.setImages(is);
		return f;
	}
	
}
