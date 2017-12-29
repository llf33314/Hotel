package com.gt.hotel.controller.erp;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.erp.ErpRoomCategoryParam;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.erp.ErpRoomCategoryVo;
import com.gt.hotel.web.service.TRoomCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 酒店ERP - 前台
 *
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:50
 */
@Api(tags = "酒店ERP 前台")
@RestController
@RequestMapping("/erp/reception")
public class ErpHotelReceptionController extends BaseController {

    @Autowired
    private WXMPApiUtil WXMPApiUtil;

    @Autowired
    private WebServerConfigurationProperties properties;

    @Autowired
    private TRoomCategoryService roomCategoryService;

    /**
     * 获取房型下的 客房列表与状态信息
     *
     * @return ResponseDTO
     */
    @ApiOperation(value = "获取房态列表数据", notes = "各房型下的各个房态列表")
    @GetMapping(value = "/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<ErpRoomCategoryVo>> index(@PathVariable("hotelId") Integer hotelId, ErpRoomCategoryParam.RoomCategorySearch categorySearch) {
        List<ErpRoomCategoryVo> roomList = roomCategoryService.findErpGroupRoomList(hotelId,categorySearch);
        return ResponseDTO.createBySuccess(roomList);
    }


}
