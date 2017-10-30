package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TAuthorizationDAO;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.ERPParameter.AuthorSave;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.AuthorizationVo;
import com.gt.hotel.web.service.TAuthorizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * ERP 功能授权管理 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-19
 */
@Service
public class TAuthorizationServiceImpl extends BaseServiceImpl<TAuthorizationDAO, TAuthorization> implements TAuthorizationService {

    @Autowired
    TAuthorizationDAO tAuthorizationDAO;

    @SuppressWarnings("unchecked")
	@Override
    public Page<AuthorizationVo> queryAuthor(Integer hotelId, HotelPage param) {
    	Page<AuthorizationVo> page = param.initPage();
    	page.setRecords(tAuthorizationDAO.queryAuthor(hotelId, param, page));
    	return page;
    }

    @Override
    public void delAuthor(Integer busId, List<Integer> ids) {
        Wrapper<TAuthorization> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        TAuthorization entity = new TAuthorization();
        entity.setUpdatedAt(new Date());
        entity.setUpdatedBy(busId);
        entity.setMarkModified(CommonConst.DELETED);
        if (!this.update(entity, wrapper)) {
        	throw new ResponseEntityException(ResponseEnums.DELETE_ERROR);
        }
    }

    @Override
    public void saveAuthor(Integer busid, List<AuthorSave> authors) {
        List<TAuthorization> al = new ArrayList<>();
        Date date = new Date();
        for (AuthorSave a : authors) {
            TAuthorization _a = new TAuthorization();
            BeanUtils.copyProperties(a, _a);
            _a.setCreatedAt(date);
            _a.setCreatedBy(busid);
            _a.setUpdatedAt(date);
            _a.setUpdatedBy(busid);
        }
        if (!this.insertBatch(al)) {
        	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
    }

}
