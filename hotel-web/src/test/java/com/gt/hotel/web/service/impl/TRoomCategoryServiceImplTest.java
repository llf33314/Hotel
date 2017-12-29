package com.gt.hotel.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.gt.hotel.BasicTest;
import com.gt.hotel.param.erp.ErpRoomCategoryParam;
import com.gt.hotel.vo.erp.ErpRoomCategoryVo;
import com.gt.hotel.web.service.TRoomCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <pre>
 * 1.0.0 2017/12/28 zhangmz -
 * ---------------------------------------------------------------------------------------
 * Version Date Author Description
 * ---------------------------------------------------------------------------------------
 * </pre>
 *
 * @author zhangmz
 * @version 1.0.0
 * @date 2017-12-28
 * @since 1.0.0
 */
@Slf4j
public class TRoomCategoryServiceImplTest extends BasicTest {

    @Autowired
    private TRoomCategoryService tRoomCategoryService;


    @Test
    public void findErpGroupRoomList() throws Exception {
        Integer hotelid = 6337;
        ErpRoomCategoryParam.RoomCategorySearch categorySearch = new ErpRoomCategoryParam.RoomCategorySearch();
        //categorySearch.setRoomNum("2");
        categorySearch.setFloor("3");
        log.info("categorySearch : {}", categorySearch);
        List<ErpRoomCategoryVo> roomList = tRoomCategoryService.findErpGroupRoomList(hotelid, categorySearch);
        Assert.assertNotNull(roomList);
        for (ErpRoomCategoryVo roomCategoryVo : roomList) {
            log.info("roomCatrgory Name : {} count : {} \n roomListSize : {} \n 预订客房数量：{}  \n roomList : {}",
                    roomCategoryVo.getName(), roomCategoryVo.getRoomCount(), roomCategoryVo.getRoomList().size(), roomCategoryVo.getBookingCount(), JSON.toJSONString(roomCategoryVo.getRoomList()));
        }
    }

}