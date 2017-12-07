package com.gt.hotel.service;

import com.gt.hotel.BasicTest;
import com.gt.hotel.dao.TOrderDAO;
import com.gt.hotel.dao.TRoomCategoryDAO;
import com.gt.hotel.dao.TRoomDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/12/06
 * @since
 */
public class BookingServiceTest extends BasicTest{

    @Autowired
    private TOrderDAO tOrderDAO;

    @Autowired
    private TRoomDAO tRoomDAO;

    @Autowired
    private TRoomCategoryDAO tRoomCategoryDAO;



    @Test
    public void validRoom(){
        // 1. 获取周期内的客房

    }

}
