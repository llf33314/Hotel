package com.gt.hotel.dao;

import com.alibaba.fastjson.JSONObject;
import com.gt.hotel.BasicTest;
import com.gt.hotel.vo.MobileHotelVo;
import lombok.extern.slf4j.Slf4j;
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
public class THotelDAOTest extends BasicTest {

    @Autowired
    private THotelDAO tHotelDAO;

    @Test
    public void findHotelVoById() throws Exception {
        MobileHotelVo vo = tHotelDAO.findHotelVoById(6312);
        log.info("vo : {}", JSONObject.toJSONString(vo));

    }

}