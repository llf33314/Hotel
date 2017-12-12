package com.gt.hotel.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.dao.TRoomDAO;
import com.gt.hotel.entity.TBreakfastCoupons;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.entity.TOrderRoomCustomer;
import com.gt.hotel.entity.TRoomCategory;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelOrderParameter.CheckInParam;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.OffLineOrder;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.param.HotelOrderRoomParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.BusinessConditionsVo;
import com.gt.hotel.vo.DepositVo;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.vo.IncomeDetailsVo;
import com.gt.hotel.vo.OrderRoomCustomerVo;
import com.gt.hotel.web.service.TBreakfastCouponsService;
import com.gt.hotel.web.service.TOrderRoomCustomerService;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;
import com.gt.hotel.web.service.TRoomCategoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-27
 */
@Slf4j
@Service
public class TOrderServiceImpl extends BaseServiceImpl<TOrderDAO, TOrder> implements TOrderService {

    @Autowired
    TOrderDAO tOrderDAO;

    @Autowired
    TOrderRoomCustomerService tOrderRoomCustomerService;

    @Autowired
    TOrderRoomService tOrderRoomService;

    @Autowired
    private TRoomDAO tRoomDAO;
    
    @Autowired
    TRoomCategoryService tRoomCategoryService;
    
    @Autowired
    TBreakfastCouponsService breakfastCouponsService;

    @SuppressWarnings("unchecked")
    @Override
    public Page<HotelBackRoomOrderVo> queryRoomOrder(Integer busId, RoomOrderQuery param) {
        Page<HotelBackRoomOrderVo> page = param.initPage();
        page.setRecords(tOrderDAO.queryRoomOrder(busId, param, page));
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<HotelBackFoodOrderVo> queryFoodOrder(Integer busId, FoodOrderQuery param) {
        Page<HotelBackFoodOrderVo> page = param.initPage();
        page.setRecords(tOrderDAO.queryFoodOrder(busId, param, page));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOffLineOrder(Integer busId, OffLineOrder order) {
        // 添加线下订单
        // 时间轴上的客房，在某一时段被占用着。但平行着的客房房间是不会出现

        // TODO: 1. 需要检测 客房状态是否 已锁定或已预订

//        // 客房ID列表
//        List<Integer> ids = new ArrayList<>();
//        // 预订客房数
//        int bookingCount = order.getRooms().size();
//        for (HotelOrderRoomParameter.OrderRoom orderRoom : order.getRooms()) {
//            ids.add(orderRoom.getRoomId());
//        }
//        // 此订单与预订客房数必须相匹配，否则订单无法成功构成
//        if (this.tRoomDAO.checkValidRooms(ids) != bookingCount) {
//            log.warn("无法创建订单，部分客房已被占用！");
//            throw new BusinessException(ResponseEnums.GUEST_ROOM_HAS_BEEN_USED);
//        }

        Date date = new Date();
        TOrder o = new TOrder();
        BeanUtils.copyProperties(order, o);
        o.setBusId(busId);
        o.setOrderNum("DD" + date.getTime());
        o.setPayStatus(CommonConst.PAY_STATUS_PAID);
        o.setPayTime(date);
        o.setCreateTime(date);
        o.setCreatedAt(date);
        o.setCreatedBy(busId);
        o.setUpdatedAt(date);
        o.setUpdatedBy(busId);
        o.setBillPrice(o.getRealPrice());
        o.setReceivablePrice(o.getRealPrice());
        if (!o.insert()) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }

        TOrderRoom or = new TOrderRoom();
        BeanUtils.copyProperties(order, or);
        or.setOrderId(o.getId());
        or.setOrderNum(o.getOrderNum());
        or.setCheckInWay(CommonConst.CHECK_IN_WAY_OFFLINE);
        or.setNumber(order.getRooms().size());
        or.setOrderFrom(CommonConst.SOURCE_BACK);
        or.setPayTime(date);
        or.setPayStatus(CommonConst.PAY_STATUS_PAID);
        or.setCreateTime(date);
        or.setCreatedAt(date);
        or.setCreatedBy(busId);
        or.setUpdatedAt(date);
        or.setUpdatedBy(busId);
        if (!or.insert()) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }

        List<TOrderRoomCustomer> orcs = new ArrayList<>();
        for (HotelOrderRoomParameter.OrderRoom hor : order.getRooms()) {
            TOrderRoomCustomer orc = new TOrderRoomCustomer();
            orc.setName(order.getCustomerName());
            orc.setIdType(order.getCustomerIdType());
            orc.setPhone(order.getCustomerPhone());
            orc.setIdCard(order.getCustomerIdCard());
            orc.setOrderId(o.getId());
            orc.setRoomNum(hor.getRoomNum());
            orc.setCustomerGender(order.getCustomerGender());
            orc.setCreatedAt(date);
            orc.setCreatedBy(busId);
            orc.setUpdatedAt(date);
            orc.setUpdatedBy(busId);
            orcs.add(orc);
        }
        if (orcs.size() != 0) {
            if (!tOrderRoomCustomerService.insertBatch(orcs)) {
                throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
            }
        }
        TRoomCategory category = tRoomCategoryService.selectById(order.getCategoryId());
		if(category.getBreakfastEnable().equals(CommonConst.ENABLED)) {
			List<TBreakfastCoupons> bcs = new ArrayList<>();
			for(int i = 0; i < orcs.size(); i++) {
				for(int j = 0; j < category.getBreakfastQuantity(); j++) {
					TBreakfastCoupons breakfastCoupons = new TBreakfastCoupons();
					breakfastCoupons.setOrderId(o.getId());
					breakfastCoupons.setCategoryId(order.getCategoryId());
					breakfastCoupons.setRoomNum(orcs.get(i).getRoomNum());
					breakfastCoupons.setCode((System.currentTimeMillis() + Math.random() * 9999999) + "");
					breakfastCoupons.setWriteOffStatus(0);
					breakfastCoupons.setCreatedAt(date);
					breakfastCoupons.setCreatedBy(busId);
					breakfastCoupons.setUpdatedBy(busId);
					bcs.add(breakfastCoupons);
				}
			}
			if(!breakfastCouponsService.insertBatch(bcs)) {
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
			}
		}
    }

    @Override
    public HotelBackRoomOrderVo queryRoomOrderOne(Integer id) {
        HotelBackRoomOrderVo orderVo = new HotelBackRoomOrderVo();
        RoomOrderQuery param = new RoomOrderQuery();
        param.setId(id);
        List<HotelBackRoomOrderVo> l = tOrderDAO.queryRoomOrder(null, param, new Pagination());
        if (l.size() == 0) {
            return null;
        }
        orderVo = l.get(0);
        orderVo.setRooms(tOrderDAO.queryRoomOrderOneRooms(id));
        return orderVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkIn(Integer busId, Integer orderId, CheckInParam param) {
        Date date = new Date();

        Wrapper<TOrder> owrapper = new EntityWrapper<>();
        owrapper.eq("id", orderId);
        TOrder newOrder = new TOrder();
        newOrder.setOrderStatus(CommonConst.ORDER_CHECK_IN);
        newOrder.setUpdatedBy(busId);
        newOrder.setUpdatedAt(new Date());
        if (!this.update(newOrder, owrapper)) {
            throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
        }

        TOrderRoom orderRoom = new TOrderRoom();
        List<TOrderRoomCustomer> customers = new ArrayList<>();
        orderRoom.setCustomerIdType(param.getCustomerIdType());
        orderRoom.setCustomerIdCard(param.getCustomerIdCard());
        orderRoom.setCustomerGender(param.getCustomerGender());
        orderRoom.setUpdatedAt(date);
        orderRoom.setUpdatedBy(busId);
        Wrapper<TOrderRoom> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id", orderId);
        if (!tOrderRoomService.update(orderRoom, wrapper)) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
        for (HotelOrderRoomParameter.OrderRoom o : param.getRooms()) {
            TOrderRoomCustomer customer = new TOrderRoomCustomer();
            customer.setIdType(param.getCustomerIdType());
            customer.setIdCard(param.getCustomerIdCard());
            customer.setCustomerGender(param.getCustomerGender());
            customer.setRoomNum(o.getRoomNum());
            customer.setOrderId(orderId);
            customer.setName(param.getCustomerName());
            customer.setPhone(param.getCustomerPhone());
            customer.setCreatedAt(date);
            customer.setCreatedBy(busId);
            customer.setUpdatedAt(date);
            customer.setUpdatedBy(busId);
            customers.add(customer);
        }
        if (!tOrderRoomCustomerService.insertBatch(customers)) {
            throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
        }
        TOrderRoom tOrderRoom = tOrderRoomService.selectOne(wrapper);
        TRoomCategory category = tRoomCategoryService.selectById(tOrderRoom.getCategoryId());
		if(category.getBreakfastEnable().equals(CommonConst.ENABLED)) {
			List<TBreakfastCoupons> bcs = new ArrayList<>();
			for(int i = 0; i < customers.size(); i++) {
				for(int j = 0; j < category.getBreakfastQuantity(); j++) {
					TBreakfastCoupons breakfastCoupons = new TBreakfastCoupons();
					breakfastCoupons.setOrderId(orderId);
					breakfastCoupons.setCategoryId(tOrderRoom.getCategoryId());
					breakfastCoupons.setRoomNum(customers.get(i).getRoomNum());
					breakfastCoupons.setCode((System.currentTimeMillis() + Math.random() * 9999999) + "");
					breakfastCoupons.setWriteOffStatus(0);
					breakfastCoupons.setCreatedAt(date);
					breakfastCoupons.setCreatedBy(busId);
					breakfastCoupons.setUpdatedBy(busId);
					bcs.add(breakfastCoupons);
				}
			}
			if(!breakfastCouponsService.insertBatch(bcs)) {
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR);
			}
		}
    }

    @Override
    public List<HotelBackRoomOrderVo> queryRoomOrderExport(Integer busId, RoomOrderQuery param) {
        return tOrderDAO.queryRoomOrder(busId, param);
    }

    @Override
    public List<HotelBackFoodOrderVo> queryFoodOrderExport(Integer busId, FoodOrderQuery param) {
        return tOrderDAO.queryFoodOrder(busId, param);
    }

    @Override
    public void mobileCheckNewOrder(Integer busId, Integer hid, Integer cid, DateTime startTime, DateTime endTime) {
        //1. 检测当前房型是否已存在预订订单

        //2. 房价信息 当前房型是否存在日历价 客户是否是协议单位

        //2.1 当前房型

        //2.2

    }

    public void mobileCheckOrdeRoom(Integer hid, Integer cid, DateTime startTime, DateTime endTime) {
        // 检测当前酒店hid 的 房型cid

    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<HotelBackRoomOrderVo> queryMobileRoomOrder(Member member, HotelPage hotelPage) {
        Page<HotelBackRoomOrderVo> page = hotelPage.initPage();
        RoomOrderQuery roq = new RoomOrderQuery();
        roq.setMemberId(member.getId());
        page.setRecords(tOrderDAO.queryRoomOrder(null, roq, page));
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<HotelBackFoodOrderVo> queryMobileFoodOrder(Member member, HotelPage hotelPage) {
        Page<HotelBackFoodOrderVo> page = hotelPage.initPage();
        FoodOrderQuery param = new FoodOrderQuery();
        param.setMemberId(member.getId());
        page.setRecords(tOrderDAO.queryFoodOrder(null, param, page));
        return page;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Page<DepositVo> queryMobileDeposit(Member member, HotelPage hotelPage) {
        Page<DepositVo> page = hotelPage.initPage();
        page.setRecords(tOrderDAO.queryMobileDeposit(member.getId(), page));
        return page;
    }

    @Override
    public Page<com.gt.hotel.vo.HotelBackRoomOrderVo> checkInOrder(Member member) {
        Page<com.gt.hotel.vo.HotelBackRoomOrderVo> page = new Page<>();
        List<HotelBackRoomOrderVo> l = tOrderDAO.checkInOrder(member.getId());
        List<Integer> ids = new ArrayList<>();
        for (HotelBackRoomOrderVo r : l) {
            ids.add(r.getId());
        }
        Wrapper<TOrderRoomCustomer> wrapper = new EntityWrapper<>();
        wrapper.in("order_id", ids);
        List<TOrderRoomCustomer> rl = tOrderRoomCustomerService.selectList(wrapper);
        for (HotelBackRoomOrderVo r : l) {
            List<OrderRoomCustomerVo> rooms = new ArrayList<>();
            for (TOrderRoomCustomer or : rl) {
                if (or.getOrderId().equals(r.getId())) {
                    OrderRoomCustomerVo orv = new OrderRoomCustomerVo();
                    BeanUtils.copyProperties(or, orv);
                    rooms.add(orv);
                }
            }
            r.setRooms(rooms);
        }
        page.setRecords(l);
        return page;
    }

    @Override
    public BusinessConditionsVo erpGetBusinessConditions(Integer busId, Integer shopId, String now) {
        String nowStart = now + " 00:00:00";
        String nowEnd = now + " 23:59:59";
        return tOrderDAO.erpGetBusinessConditions(busId, shopId, nowStart, nowEnd);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<IncomeDetailsVo> erpGetIncomeDetails(Integer busId, Integer shopId, HotelPage hpage) {
        Page<IncomeDetailsVo> page = hpage.initPage();
        List<IncomeDetailsVo> l = tOrderDAO.getIncomeDetailsByDate(busId, shopId, hpage, page);
        //TODO 暂缺 商品消费 & 其他
        page.setRecords(l);
        return page;
    }

}
