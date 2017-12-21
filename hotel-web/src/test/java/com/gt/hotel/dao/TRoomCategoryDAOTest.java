package com.gt.hotel.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.BasicTest;
import com.gt.hotel.param.RoomCategoryParameter;
import com.gt.hotel.vo.MobileRoomCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/12/11
 * @since
 */
@Slf4j
public class TRoomCategoryDAOTest extends BasicTest {

    @Autowired
    private TRoomCategoryDAO tRoomCategoryDAO;

    /**
     * 测试客房列表信息
     *
     * @throws Exception
     */
   /* @Test
    public void findMobileRoomCategoryVoList() throws Exception {
        Page<MobileRoomBookableVo> page = new Page<>(1, 10);
        // 获取每个房型可预约的间数
        RoomCategoryParameter.MobileQueryRoomCategory req = new RoomCategoryParameter.MobileQueryRoomCategory();
        req.setRoomInTime("2017-12-15");
        req.setRoomOutTime("2017-12-27");
        List<MobileRoomBookableVo> roomCategoryVoList = this.tRoomCategoryDAO.findMobileRoomCategoryVoList(page, 3, req);
        Assert.assertNotNull(roomCategoryVoList);
        for (MobileRoomBookableVo record : roomCategoryVoList) {
            log.info("MobileRoomBookableVo : {}", JSONObject.toJSONString(record));
            for (FileRecordVo recordVo : record.getImages()) {
                log.info(recordVo.getName());
            }
            for (InfrastructureRelationVo relationVo : record.getInfrastructureRelations()) {
                log.info("{}", relationVo);
            }
        }

    }*/
    @Test
    public void queryMobileRoomCategory() {
        // 获取每个房型可预约的间数
        RoomCategoryParameter.MobileQueryRoomCategory req = new RoomCategoryParameter.MobileQueryRoomCategory();
        req.setRoomInTime("2017-12-15");
        req.setRoomOutTime("2017-12-27");
        Page<MobileRoomCategoryVo> page = req.initPage();
        List<MobileRoomCategoryVo> roomCategoryVos = this.tRoomCategoryDAO.queryMobileRoomCategory(3, req, page);
        for (MobileRoomCategoryVo categoryVo : roomCategoryVos) {
            log.info("categoryVo : {}", categoryVo);
        }
    }


}