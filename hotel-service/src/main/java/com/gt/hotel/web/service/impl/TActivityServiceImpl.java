package com.gt.hotel.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TActivityDAO;
import com.gt.hotel.entity.TActivity;
import com.gt.hotel.entity.TActivityDetail;
import com.gt.hotel.entity.TActivityRoom;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.ActivityParamter;
import com.gt.hotel.param.ActivityParamter.ActivityRoomParam;
import com.gt.hotel.vo.ActivityDetailVo;
import com.gt.hotel.vo.ActivityRoomVo;
import com.gt.hotel.vo.ActivityVo;
import com.gt.hotel.web.service.TActivityDetailService;
import com.gt.hotel.web.service.TActivityRoomService;
import com.gt.hotel.web.service.TActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动设置 - 抽出活动公共设置 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class TActivityServiceImpl extends BaseServiceImpl<TActivityDAO, TActivity> implements TActivityService {

    @Autowired
    TActivityDAO tActivityDAO;

    @Autowired
    TActivityRoomService tActivityRoomService;

    @Autowired
    TActivityDetailService tActivityDetailService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<ActivityVo> queryActivity(ActivityParamter.ActivityQuery param) {
        Page<ActivityVo> page = param.initPage();
        page.setRecords(tActivityDAO.queryActivity(param, page));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editActivity(Integer busid, ActivityParamter.ActivitySaveOrUpdate arooms) {
        Date date = new Date();
        TActivity a = new TActivity();
        TActivityDetail ad = new TActivityDetail();

        BeanUtils.copyProperties(arooms, a);
        BeanUtils.copyProperties(arooms, ad);
        //保存活动
        a.setActivityNum("HD" + date.getTime());
        if (a.getId() == null) {
            a.setCreatedAt(date);
            a.setCreatedBy(busid);
        }
        a.setUpdatedAt(date);
        a.setUpdatedBy(busid);
        if (!a.insertOrUpdate()) {
            throw new ResponseEntityException("活动保存失败");
        }
        //保存活动详情
        ad.setActivityId(a.getId());
        ad.setUpdatedAt(date);
        ad.setUpdatedBy(busid);
        if (arooms.getId() == null) {
            ad.setCreatedAt(date);
            ad.setCreatedBy(busid);
            if (!ad.insert()) {
                throw new ResponseEntityException("活动详情保存失败");
            }            
        }else {
        	Wrapper<TActivityDetail> wrapper = new EntityWrapper<>();
        	wrapper.eq("activity_id", a.getId());
			if (!tActivityDetailService.update(ad, wrapper)) {
        		throw new ResponseEntityException("活动详情保存失败");
        	}
        }
        //保存活动房间
        Wrapper<TActivityRoom> wrapper = new EntityWrapper<>();
        wrapper.eq(a.getId() != null, "activity_id", a.getId());
        TActivityRoom entity = new TActivityRoom();
        entity.setMarkModified(CommonConst.DELETED);
        entity.setUpdatedBy(busid);
        entity.setUpdatedAt(date);
        tActivityRoomService.update(entity, wrapper);

        List<TActivityRoom> ars = new ArrayList<>();
        List<Integer> arsids = new ArrayList<>();
        for (ActivityRoomParam arp : arooms.getRooms()) {
            TActivityRoom ar = new TActivityRoom();
            BeanUtils.copyProperties(arp, ar);
            if(ar.getId() == null) {
            	ar.setActivityId(a.getId());
            	ar.setMarkModified(0);
            	ar.setCreatedAt(date);
            	ar.setCreatedBy(busid);
            	ar.setUpdatedAt(date);
            	ar.setUpdatedBy(busid);
            	ars.add(ar);
            }else {
            	arsids.add(ar.getId());
            }
        }
		if (!tActivityRoomService.insertBatch(ars)) {
			throw new ResponseEntityException("活动房间保存失败");
		}
		Wrapper<TActivityRoom> wrapperII = new EntityWrapper<>();
		wrapperII.in("id", arsids);
		TActivityRoom arsII = new TActivityRoom();
		arsII.setMarkModified(CommonConst.ENABLED);
		tActivityRoomService.update(arsII, wrapperII);
    }

    @Override
    public ActivityVo queryActivityOne(Integer id) {
        ActivityVo av = new ActivityVo();
        TActivity a = this.selectById(id);
        BeanUtils.copyProperties(a, av);

        Wrapper<TActivityDetail> dw = new EntityWrapper<>();
        dw.eq("activity_id", id);
        TActivityDetail ad = tActivityDetailService.selectOne(dw);
        ActivityDetailVo adv = new ActivityDetailVo();
        BeanUtils.copyProperties(ad, adv);
        av.setDetail(adv);

        Wrapper<TActivityRoom> rw = new EntityWrapper<>();
        rw.eq("activity_id", id);
        List<TActivityRoom> ars = tActivityRoomService.selectList(rw);
        List<ActivityRoomVo> arvs = new ArrayList<>();
        for (TActivityRoom ar : ars) {
            ActivityRoomVo arv = new ActivityRoomVo();
            BeanUtils.copyProperties(ar, arv);
            arvs.add(arv);
        }

        return av;
    }
    
}
