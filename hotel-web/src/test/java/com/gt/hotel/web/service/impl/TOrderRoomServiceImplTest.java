package com.gt.hotel.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.gt.api.bean.session.Member;
import com.gt.hotel.BasicTest;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.param.RoomMobileParameter;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TOrderRoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * 1.0.0 2017/12/21 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-21
 * @since 1.0.0
 */
@Slf4j
public class TOrderRoomServiceImplTest extends BasicTest{

    @Autowired
    private TOrderRoomService tOrderRoomService;

    @Autowired
    private THotelService tHotelService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Test
    public void mobileBookOrder() throws Exception {
        THotel hotel = this.tHotelService.selectById(3);
        // 获取会员信息
        Optional<JSONObject> memberInfo = Optional.of(this.wxmpApiUtil.findCardByMembeId(28, 33));
        Member member = JSON.toJavaObject(memberInfo.get(), Member.class);
        RoomMobileParameter.BookParam bookParam = new RoomMobileParameter.BookParam();
        tOrderRoomService.mobileBookOrder(hotel,member,null);


    }

}