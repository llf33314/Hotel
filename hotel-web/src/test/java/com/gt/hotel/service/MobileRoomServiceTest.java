package com.gt.hotel.service;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gt.api.bean.session.Member;
import com.gt.hotel.BasicTest;
import com.gt.hotel.param.RoomMobileParameter.BookParam;
import com.gt.hotel.vo.RoomOrderPriceVO;
import com.gt.hotel.web.service.TOrderRoomService;

/**
 * 
 * @author Reverien9@gmail.com
 * 2017年11月22日 下午6:50:17
 */
public class MobileRoomServiceTest extends BasicTest {
	
	@Autowired
	TOrderRoomService tOrderRoomService;
	
	@Test
	public void testPrice() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//test
    	Member member1 = new Member();
    	member1.setId(1071);
    	member1.setBusid(33);
    	member1.setPhone("13433550667");
    	member1.setPublicId(492);
    	member1.setCardid("15338");
		//test
    	BookParam bookParam = new BookParam();
    	bookParam.setCategoryId(1);
    	bookParam.setRoomInTime(sdf.parse("2017-11-02 14:00:00"));
    	bookParam.setRoomOutTime(sdf.parse("2017-11-20 12:00:00"));
    	bookParam.setFb(20);
    	bookParam.setIntegral(200);
		RoomOrderPriceVO price = tOrderRoomService.MobilePriceCalculation(2, member1, bookParam);
		System.err.println(price);
	}
	

}