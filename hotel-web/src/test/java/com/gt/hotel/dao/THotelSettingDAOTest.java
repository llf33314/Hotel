package com.gt.hotel.dao;

import com.gt.hotel.BasicTest;
import com.gt.hotel.vo.MobileHotelVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * 1.0.0 2017/12/15 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-15
 * @since 1.0.0
 */
@Slf4j
public class THotelSettingDAOTest extends BasicTest {

    @Autowired
    private THotelSettingDAO tHotelSettingDAO;

    @Test
    public void findHotelVoById() throws Exception {
        Integer hotelId = 3;
        MobileHotelVo vo = this.tHotelSettingDAO.findHotelVoById(hotelId);
        Assert.assertNull(vo);
        log.info("vo : {}", vo);
    }


}