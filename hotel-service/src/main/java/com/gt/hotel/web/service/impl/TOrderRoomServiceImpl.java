package com.gt.hotel.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.Member;
import com.gt.hotel.base.BaseServiceImpl;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.dao.TOrderRoomDAO;
import com.gt.hotel.entity.*;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.DuofenCards;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.param.RoomMobileParameter.RoomCardParam;
import com.gt.hotel.util.DateUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.*;
import com.gt.hotel.web.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 酒店订单 客房订单 (客单) 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-27
 */
@Slf4j
@Service
public class TOrderRoomServiceImpl extends BaseServiceImpl<TOrderRoomDAO, TOrderRoom> implements TOrderRoomService {

    @Autowired
    THotelService hotelService;

    @Autowired
    TOrderService orderService;

    @Autowired
    TOrderDAO orderDAO;

    @Autowired
    TActivityDetailService activityDetailService;

    @Autowired
    TActivityRoomService activityRoomService;

    @Autowired
    TOrderRoomService orderRoomService;

    @Autowired
    TOrderCouponsService couponsService;

    @Autowired
    TOrderRoomDAO tOrderRoomDAO;

    @Autowired
    WXMPApiUtil wXMPApiUtil;

    @Autowired
    TRoomCategoryService tRoomCategoryService;

    @Autowired
    TBreakfastCouponsService breakfastCouponsService;

    @Autowired
    THotelService tHotelService;
    
    @Autowired
    TRoomCalendarService roomCalendarService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer mobileBookOrder(THotel hotel, Member member, BookParam bookParam) throws ParseException {
        // TODO: 2017/12/20 需要增加客房历史流水记录，方便跟踪入住客房房价历史。
        // FIXME: 2017/12/20 修订业务操作 创建订单多笔订单数量 Bug
        try {
            RoomOrderPriceVO orderPriceVO = mobilePriceCalculation(hotel.getId(), member, bookParam);
            if (!bookParam.getPayPrice().equals(orderPriceVO.getPayPrice())) {
                log.warn("bookParam Price : {} , orderPriceVo Price : {}", bookParam.getPayPrice(), orderPriceVO.getPayPrice());
                throw new ResponseEntityException(ResponseEnums.PRICE_FAILED);
            }
        } catch (Exception e) {
            log.error("", e);
            throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
        }
        Date date = new Date();
        TOrder order = new TOrder();
        TOrderCoupons coupons = new TOrderCoupons();
        BeanUtils.copyProperties(bookParam, order);
        BeanUtils.copyProperties(bookParam, coupons);
        order.setOrderNum("DD" + System.currentTimeMillis());
        order.setHotelId(hotel.getId());
        order.setBusId(member.getBusid());
        order.setMemberId(member.getId());
        order.setCreateTime(date);
        order.setRealPrice(bookParam.getPayPrice());
        order.setBillPrice(bookParam.getPayPrice());
        order.setReceivablePrice(bookParam.getPayPrice());
        order.setCreatedAt(date);
        order.setCreatedBy(member.getId());
        order.setUpdatedBy(member.getId());
        if (!orderService.insert(order)) {
            throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
        }
        // 2017/12/21: 创建多间客房订单  by:zhangmz
        List<TOrderRoom> orderRoomList = new ArrayList<>();
        for (int i = 0; i < order.getRoomOrderNum(); i++) {
            TOrderRoom orderRoom = new TOrderRoom();
            BeanUtils.copyProperties(bookParam, orderRoom);
            orderRoom.setOrderId(order.getId());
            orderRoom.setOrderNum(order.getOrderNum());
            orderRoom.setHotelId(hotel.getId());
            orderRoom.setHotelName(hotel.getName());
            orderRoom.setReceivablePrice(bookParam.getPayPrice());
            orderRoom.setRoomPrice(bookParam.getDisplayPrice());
            orderRoom.setOrderFrom(CommonConst.SOURCE_MOBILE);
            orderRoom.setGuestType(0);
            orderRoom.setCreatedAt(date);
            orderRoom.setCreateTime(date);
            orderRoom.setCreatedBy(member.getId());
            orderRoom.setUpdatedBy(member.getId());
            if (bookParam.getHourRoomCheckInTime() != null) {
                orderRoom.setHourRoomCheckInTime(new SimpleDateFormat("HH:mm:ss").parse(bookParam.getHourRoomCheckInTime()));
            }
            orderRoomList.add(orderRoom);
        }
        // 批量插入
        if (!orderRoomService.insertBatch(orderRoomList)) {
            log.error("客房订单创建失败");
            throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
        }
        // 使用了优惠券
        if (bookParam.getCouponsCode() != null) {
            coupons.setOrderId(order.getId());
            coupons.setOrderNum(order.getOrderNum());
            coupons.setCouponsCode(bookParam.getCouponsCode());
            coupons.setCouponsNum(bookParam.getCouponsNum() == null ? 0 : bookParam.getCouponsNum());
            if (!couponsService.insert(coupons)) {
                throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
            }
            Wrapper<TOrder> wrapper = new EntityWrapper<>();
            wrapper.eq("id", order.getId());
            TOrder orderII = new TOrder();
            orderII.setOrderCouponsId(coupons.getId());
            if (!orderService.update(orderII, wrapper)) {
                throw new ResponseEntityException(ResponseEnums.BOOK_FAILED);
            }
        }
        return order.getId();
    }


    @Override
    public MobileRoomOrderVo queryMobileRoomOrderOne(Integer hotelId, Integer orderId, Member member) {
        MobileRoomOrderVo mobileRoomOrderVo = tOrderRoomDAO.queryMobileRoomOrderOne(hotelId, orderId, member.getId());
        if (mobileRoomOrderVo.getActivityId() != null) {
            ActivityDetailVo activityDetailVo = new ActivityDetailVo();
            Wrapper<TActivityDetail> wrapper = new EntityWrapper<>();
            wrapper.eq("activity_id", mobileRoomOrderVo.getActivityId());
            TActivityDetail activityDetail = activityDetailService.selectOne(wrapper);
            if (activityDetail != null) {
                BeanUtils.copyProperties(activityDetail, activityDetailVo);
            }
            mobileRoomOrderVo.setActivityDetailVo(activityDetailVo);
        }
        return mobileRoomOrderVo;
    }

    @Transactional
    @Override
    public JSONObject moblieHotelRoomPayNotifyUrl(Map<String, Object> param, Integer orderId) {
        JSONObject json = new JSONObject();
        if (param.get("out_trade_no") == null) {
            throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
        }
        int payType = Integer.valueOf(param.get("payType").toString());
        Date date = new Date();
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("id", orderId);
        wrapper.eq("order_num", param.get("out_trade_no"));
        TOrder tOrder = orderService.selectOne(wrapper);
        tOrder.setPayStatus(CommonConst.PAY_STATUS_PAID);
        tOrder.setPayTime(date);
        tOrder.setPayType(payType == 0 ? CommonConst.PAY_TYPE_WX : CommonConst.PAY_TYPE_ALI);
        if (!tOrder.updateById()) {
            throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
        }
        Wrapper<TOrderRoom> fwrapper = new EntityWrapper<>();
        fwrapper.eq("order_id", orderId);
        fwrapper.eq("order_num", param.get("out_trade_no"));
        TOrderRoom orderRoom = orderRoomService.selectOne(fwrapper);
        orderRoom.setPayStatus(CommonConst.PAY_STATUS_PAID);
        orderRoom.setPayTime(date);
        if (!orderRoom.updateById()) {
            throw new ResponseEntityException(ResponseEnums.OPERATING_ERROR);
        }
        json.put("code", 0);
        json.put("msg", "支付成功");
        return json;
    }

    @Override
    public RoomOrderPriceVO mobilePriceCalculation(Integer hotelId, Member member, BookParam bookParam) throws Exception {
        RoomOrderPriceVO orderPriceVO = new RoomOrderPriceVO();
        Integer price = 0;
        /* 日期 */
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        List<Long> timeList = new ArrayList<Long>();
//        Integer days = DateUtil.differentDays(bookParam.getRoomInTime(), bookParam.getRoomOutTime());
//        days = days == 0 ? 1 : days;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(bookParam.getRoomInTime());
//        cal.add(Calendar.DAY_OF_YEAR, -1);
//        int ordinaryDays = 0;
//        int weekendDays = 0;
//        for (int i = 0; i < days; i++) {
//            cal.add(Calendar.DAY_OF_YEAR, 1);
//            int week = cal.get(Calendar.DAY_OF_WEEK);
//            if (week == Calendar.FRIDAY || week == Calendar.SATURDAY) {
//                weekendDays++;
//            } else {
//                ordinaryDays++;
//            }
//            timeList.add(cal.getTimeInMillis());
//        }
        /* 日期 */
        THotel hotel = tHotelService.selectById(hotelId);
        Wrapper<TRoomCategory> w = new EntityWrapper<>();
        w.eq("hotel_id", hotelId);
        w.eq("id", bookParam.getCategoryId());
        List<TRoomCategory> categories = tRoomCategoryService.selectList(w);
        JSONObject card = null;
        if (member != null) {
            // 获取会员卡券
            JSONObject json = wXMPApiUtil.findMemberCard(member.getPhone(), member.getBusid(), hotel.getShopId());
            card = json.getJSONObject("data");
        }
        Integer days = DateUtil.differentDays(bookParam.getRoomInTime(), bookParam.getRoomOutTime());
        days = days == 0 ? 1 : days;
        for (TRoomCategory m : categories) {
            if (m.getId().equals(bookParam.getCategoryId())) {
            	Wrapper<TRoomCalendar> calw = new EntityWrapper<>();
            	w.eq("category_id", bookParam.getCategoryId());
        		List<TRoomCalendar> calList = roomCalendarService.selectList(calw);
                Calendar cal = Calendar.getInstance();
                cal.setTime(bookParam.getRoomInTime());
                cal.add(Calendar.DAY_OF_YEAR, -1);
            	for (int i = 0; i < days; i++) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    int week = cal.get(Calendar.DAY_OF_WEEK);
                    int tempPrice = m.getRackRate();
                    if ((week == Calendar.FRIDAY || week == Calendar.SATURDAY) && m.getWeekendFareEnable().equals(CommonConst.ENABLED)) {
                    	tempPrice = m.getWeekendFare();
                    }
                    for(TRoomCalendar rcal : calList) {
            			if(rcal.getBeginTime().getTime() <= cal.getTimeInMillis() && rcal.getEndTime().getTime() >= cal.getTimeInMillis()) {
            				tempPrice = rcal.getPrice();
            				break;
            			}
            		}
                    price += tempPrice;
                }
                orderPriceVO.setRoomPrice(price * bookParam.getRoomOrderNum());
                price = activityCalculate(bookParam, price, orderPriceVO, days);
                if (bookParam.getActivityId() == null) {
                	if(card != null && card.getInteger("ctId").equals(CommonConst.CARD_TYPE_DISCOUNT_CARD)) {
                		price = Double.valueOf(m.getRackRate() * card.getDouble("discount") / 10).intValue() * days;
                	}
                	orderPriceVO.setRoomPrice(price* bookParam.getRoomOrderNum());
                }
            }
        }
        price += bookParam.getDeposit();
        price *= bookParam.getRoomOrderNum();
        if (member != null && card != null) {
        	MemberCard memberCard = JSONObject.toJavaObject(card, MemberCard.class);
        	price = duofenCardsCalculate(bookParam, memberCard, price, orderPriceVO);
        	price = integralCalculate(bookParam, memberCard, price, orderPriceVO);
        	price = fenBiCalculate(bookParam, memberCard, price, orderPriceVO);
        }
        orderPriceVO.setDeposit(bookParam.getDeposit() * bookParam.getRoomOrderNum());
        orderPriceVO.setPayPrice(price);
        return orderPriceVO;
    }

	/**
	 * 活动 计算
	 * @param bookParam
	 * @param price
	 * @param orderPriceVO
	 * @param days
	 */

	private Integer activityCalculate(BookParam bookParam, Integer price, RoomOrderPriceVO orderPriceVO, Integer days) {
		if(bookParam.getActivityId() != null) {
			Wrapper<TActivityRoom> arw = new EntityWrapper<>();
			arw.eq("activity_id", bookParam.getActivityId());
			arw.eq("category_id", bookParam.getCategoryId());
			TActivityRoom ar = activityRoomService.selectOne(arw);
			price = ar.getActivityPrice() * days;
			orderPriceVO.setRoomPrice(price * bookParam.getRoomOrderNum());
		}
		return price;
	}


    /**
     * 积分 计算
     *
     * @param bookParam
     * @param memberCard
     * @param price
     * @param orderPriceVO
     */
    private Integer integralCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
        if (bookParam.getIntegral() != null) {
            if (price >= memberCard.getJifenStartMoney() * 100) {
                price -= memberCard.getJifenMoeny() * 100;
                orderPriceVO.setIntegralPrice(memberCard.getJifenMoeny() * 100);
            }
        }
        return price;
    }

    /**
     * 粉币 计算
     *
     * @param bookParam
     * @param memberCard
     * @param price
     * @param orderPriceVO
     */
    private Integer fenBiCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
        if (bookParam.getFb() != null) {
            if (price >= memberCard.getFenbiStartMoney() * 100) {
                price -= memberCard.getFenbiMoeny() * 100;
                orderPriceVO.setFenbiPrice(memberCard.getFenbiMoeny() * 100);
            }
        }
        return price;
    }

    /**
     * 卡券 计算
     *
     * @param memberCard
     * @param price
     * @param orderPriceVO
     * @return
     */
    private Integer duofenCardsCalculate(BookParam bookParam, MemberCard memberCard, Integer price, RoomOrderPriceVO orderPriceVO) {
        if (bookParam.getCouponsCode() != null) {
            for (DuofenCards dc : memberCard.getDuofenCards()) {
                if (dc.getCode().equals(bookParam.getCouponsCode())) {
                    if (dc.getCard_type() == 0) {
                        int temp = price;
                        price = Double.valueOf(price * dc.getDiscount() / 10).intValue();
                        orderPriceVO.setCouponPrice(temp - price);
                        orderPriceVO.setCouponCount(1);
                    } else {
                        if (price > (dc.getCash_least_cost() * 100)) {
                            int dcCount = duofenCardsCount(dc, price);
                            price -= dc.getReduce_cost() * 100 * dcCount;
                            orderPriceVO.setCouponPrice(dc.getReduce_cost() * 100 * dcCount);
                            orderPriceVO.setCouponCount(dcCount);
                        }
                    }
                }
            }
        }
        return price;
    }

    private int duofenCardsCount(DuofenCards dc, Integer price) {
        int dcCount = 0;
        int temp = price;
        for (int i = 0; i < dc.getCountId(); i++) {
            if (temp > dc.getCash_least_cost()) {
                temp -= dc.getReduce_cost();
                dcCount++;
            } else {
                break;
            }
        }
        return dcCount;
    }

    @Override
    public Integer queryMobileRoomOrderSUM(Integer memberId) {
        return tOrderRoomDAO.queryMobileRoomOrderSUM(memberId);
    }

    @Override
    public RoomCheackInCountVo roomCheckInCount(Integer busId, Integer shopId) {
        return tOrderRoomDAO.roomCheckInCount(busId, shopId);
    }

    @Override
    public List<CheackInListRevenueVo> erpGetOccupancyRevenue(String now, Integer busId, Integer shopId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<CheackInListRevenueVo> l = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Date date = new Date();
        if (!StringUtils.isEmpty(now)) {
            try {
                date = sdf.parse(now);
            } catch (ParseException e) {
                throw new ResponseEntityException(ResponseEnums.ERROR);
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        for (int i = 0; i < 7; i++) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
            dates.add(sdf.format(cal.getTime()));
        }
        String startDate = dates.get(dates.size() - 1);
        String endDate = dates.get(0);
        List<TOrderRoom> ors = tOrderRoomDAO.getCheckInRoom(busId, shopId, startDate, endDate);
        List<TOrder> os = orderDAO.getTotalRevenue(busId, shopId, startDate, endDate);
        int roomCount = tOrderRoomDAO.roomCheckInCount(busId, shopId).getRoomCount();
        for (String day : dates) {
            CheackInListRevenueVo cil = new CheackInListRevenueVo();
            Integer checkInRoomCount = 0;
            Integer totalRevenue = 0;
            for (TOrderRoom or : ors) {
                if (sdf.format(or.getRoomInTime()).equals(day)) {
                    checkInRoomCount += or.getNumber();
                }
            }
            for (TOrder o : os) {
                if (sdf.format(o.getPayTime()).equals(day)) {
                    totalRevenue += o.getRealPrice();
                }
            }
            cil.setCheckInRoomCount(checkInRoomCount);
            cil.setTotalRevenue(totalRevenue);
            cil.setOccupancyRate(Double.valueOf(Math.rint((checkInRoomCount / (double) roomCount * 100))).intValue());
            cil.setDate(day);
            l.add(cil);
        }
        return l;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<RoomCardVo> mobileFindRoomCard(Member member, Integer vipLevel, RoomCardParam param) {
        System.err.println(member);
        Page<RoomCardVo> page = param.initPage();
        List<RoomCardVo> cardVos = tOrderRoomDAO.findRoomCard(member.getId(), vipLevel, page);
        page.setRecords(cardVos);
        return page;
    }

}
