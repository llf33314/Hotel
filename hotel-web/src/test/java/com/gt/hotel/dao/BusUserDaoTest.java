package com.gt.hotel.dao;

import com.gt.hotel.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author zhangmz
 * @create 2017/7/9
 */
public class BusUserDaoTest extends BasicTest {

    @Autowired
    private BusUserDao busUserDao;

    @Test
    public void testSelect() {
        Integer selectCount = this.busUserDao.selectCount(null);
        this.logger.info("=====>> {}", selectCount);
    }

}