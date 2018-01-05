package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TAuthorizationDAO;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.Employee;
import com.gt.hotel.param.erp.ERPParameter;
import com.gt.hotel.param.erp.ERPParameter.AuthorSave;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.AuthorizationVo;
import com.gt.hotel.web.service.TAuthorizationService;

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
    
    @Autowired
    WXMPApiUtil WXMPApiUtil;

    @SuppressWarnings("unchecked")
	@Override
    public Page<AuthorizationVo> queryAuthor(Integer hotelId, ERPParameter.AuthorQuery param) {
    	Page<AuthorizationVo> page = param.initPage();
    	JSONObject result = WXMPApiUtil.getAllStaffShopId(param.getShopId(), null, null, null, null);
    	List<Employee> l = new ArrayList<>();
    	if ("0".equals(result.getString("code"))) {
            JSONObject temp = JSONObject.parseObject(result.getString("data"));
            l = JSONArray.parseArray(temp.getString("staffList"), Employee.class);
    	}
    	List<AuthorizationVo> al = tAuthorizationDAO.queryAuthor(hotelId, param, page);
    	for(AuthorizationVo a : al) {
    		for(Employee e : l) {
    			if(a.getAccountId().equals(e.getId())) {
    				a.setName(e.getName());
    				a.setPhone(e.getPhone());
    			}
    		}
    	}
    	page.setRecords(al);
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
            TAuthorization ta = new TAuthorization();
            BeanUtils.copyProperties(a, ta);
            ta.setCreatedAt(date);
            ta.setCreatedBy(busid);
            ta.setUpdatedAt(date);
            ta.setUpdatedBy(busid);
            al.add(ta);
        }
        if (!this.insertBatch(al)) {
        	throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
    }

}
