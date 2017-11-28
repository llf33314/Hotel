package com.gt.hotel.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.gt.hotel.HotelApplication;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.web.service.THotelService;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = HotelApplication.class)
@WebAppConfiguration
public class HotelServiceImplTest {

    @Autowired
    private THotelService tHotelService;
    
    @Autowired
    private WXMPApiUtil WXMPApiUtil;

    @Test
    public void queryHotelHome() throws Exception {
        PageHelper.startPage(1, 10);

        List<THotel> hotels = tHotelService.selectList(null);
        Assert.isNull(hotels, "空");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println(hotels.get(i).getAddress());
        }

    }
    
    @Test
    public void wxmp() throws Exception {
    	try {
			System.err.println(WXMPApiUtil.getMenus(0, 33, 0, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}