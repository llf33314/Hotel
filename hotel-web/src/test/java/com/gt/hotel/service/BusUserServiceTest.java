package com.gt.hotel.service;

import com.gt.hotel.BasicTest;
import com.gt.hotel.web.service.BusUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangmz
 * @create 2017/7/9
 */
public class BusUserServiceTest extends BasicTest {

    @Autowired
    private BusUserService busUserService;

    @Test
    public void testSelect() {
	Integer selectCount = this.busUserService.selectCount( null );
	this.logger.info( "=====>> {}", selectCount );
    }

}