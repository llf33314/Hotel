package com.gt.hotel.web.service.impl;

import com.gt.hotel.BasicTest;
import com.gt.hotel.vo.erp.ErpRoomStatusVo;
import com.gt.hotel.web.service.TRoomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * 1.0.0 2018/01/02 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2018-01-02
 * @since 1.0.0
 */
@Slf4j
public class TRoomServiceImplTest extends BasicTest{

    @Autowired
    private TRoomService roomService;

    @Test
    public void findRoomsGroupStatistics() throws Exception {
        ErpRoomStatusVo statusVo = roomService.findRoomsGroupStatistics(6337);
        Assert.assertNotNull(statusVo);
        log.info("ststusVo : {}", statusVo);
    }

}