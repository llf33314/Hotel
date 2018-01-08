package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TAgreementOrganizationDAO;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.entity.TPackageRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.erp.AgreementParamter.AgreementInsert;
import com.gt.hotel.param.erp.AgreementParamter.AgreementQuery;
import com.gt.hotel.param.erp.AgreementParamter.ReceivablesQuery;
import com.gt.hotel.param.erp.HandingParam.HandingQuery;
import com.gt.hotel.vo.erp.AgreementOrganizationVo;
import com.gt.hotel.vo.erp.HandingStatisticsVo;
import com.gt.hotel.vo.erp.HandingVo;
import com.gt.hotel.vo.erp.PackageRoomVo;
import com.gt.hotel.vo.erp.ReceivablesVo;
import com.gt.hotel.web.service.TAgreementOrganizationService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TPackageRoomService;
import com.gt.hotel.web.service.TPackageService;

/**
 * <p>
 * 针对协议组织(单位/中介 使用哪种方式的套餐 服务实现类
 * </p>
 *
 * @author
 * @since 2017-11-24
 */
@Service
public class TAgreementOrganizationServiceImpl extends BaseServiceImpl<TAgreementOrganizationDAO, TAgreementOrganization> implements TAgreementOrganizationService {

    @Autowired
    TAgreementOrganizationDAO agreementOrganizationDAO;

    @Autowired
    THotelService hotelService;

    @Autowired
    TPackageRoomService packageRoomService;

    @Autowired
    TPackageService packageService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<AgreementOrganizationVo> erpQueryAgreementOrganization(Integer hotelId, AgreementQuery query) {
        Page<AgreementOrganizationVo> page = query.initPage();
        page.setRecords(agreementOrganizationDAO.erpQueryAgreementOrganization(hotelId, query, page));
        return page;
    }

    @Override
    public void erpInsertAgreementOrganization(Integer busId, Integer hotelId, AgreementInsert insert) {
        Date date = new Date();
        if (insert.getId() == null) {
            TAgreementOrganization a = new TAgreementOrganization();
            BeanUtils.copyProperties(insert, a);
            a.setHotelId(hotelId);
            a.setCreatedAt(date);
            a.setCreatedBy(busId);
            a.setUpdatedBy(busId);
            a.setStatus(CommonConst.PENDING_REVIEW);
            if (!a.insert()) {
                throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
            }
        } else {
            TAgreementOrganization a = new TAgreementOrganization();
            BeanUtils.copyProperties(insert, a);
            if (!a.updateById()) {
                throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
            }
        }
    }

    @Override
    public AgreementOrganizationVo erpQueryAgreementOrganizationDetail(Integer id) {
        AgreementOrganizationVo a = new AgreementOrganizationVo();
        AgreementQuery query = new AgreementQuery();
        query.setId(id);
        List<AgreementOrganizationVo> l = agreementOrganizationDAO.erpQueryAgreementOrganization(null, query, query.initPage());
        if (l == null || l.size() == 0) {
            return null;
        }
        a = l.get(0);
        Wrapper<TPackageRoom> wrapper = new EntityWrapper<>();
        wrapper.eq("package_id", id);
        List<TPackageRoom> pl = packageRoomService.selectList(wrapper);
        List<PackageRoomVo> packageRoomVos = new ArrayList<>();
        for (TPackageRoom p : pl) {
            PackageRoomVo packageRoomVo = new PackageRoomVo();
            BeanUtils.copyProperties(p, packageRoomVo);
            packageRoomVos.add(packageRoomVo);
        }
        a.setPackageRoomVos(packageRoomVos);
        return a;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Page<ReceivablesVo> erpQueryReceivables(Integer hotelId, ReceivablesQuery query) {
		Page<ReceivablesVo> page = query.initPage();
		page.setRecords(agreementOrganizationDAO.erpQueryReceivables(hotelId, query, page));
		return page;
	}

	@Override
	public Long erpQueryReceivablesTotalPrice(Integer hotelId) {
		return agreementOrganizationDAO.erpQueryReceivablesTotalPrice(hotelId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<HandingVo> erpQueryHandingList(Integer hotelId, HandingQuery param) {
		Page<HandingVo> page = param.initPage();
		page.setRecords(agreementOrganizationDAO.erpQueryHandingList(hotelId, param, page));
		return page;
	}

	@Override
	public HandingStatisticsVo erpQueryHandingStatistics(Integer hotelId) {
		return agreementOrganizationDAO.erpQueryHandingStatistics(hotelId);
	}
}
