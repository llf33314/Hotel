package com.gt.hotel.service;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.gt.hotel.BasicTest;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.web.service.THotelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/10/10
 */
public class HotelServiceImplTest extends BasicTest {

    @Autowired
    private THotelService tHotelService;

    @Test
    public void queryHotelHome() throws Exception {
        PageHelper.startPage(1, 10);

        List<THotel> hotels = tHotelService.selectList(null);
        Assert.isNull(hotels, "空");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println(hotels.get(i).getAddress());
        }

    }

}