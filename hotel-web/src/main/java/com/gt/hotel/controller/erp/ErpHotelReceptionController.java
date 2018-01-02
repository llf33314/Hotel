package com.gt.hotel.controller.erp;

import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.param.erp.ErpRoomCategoryParam;
import com.gt.hotel.properties.WebServerConfigurationProperties;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.erp.ErpRoomCategoryVo;
import com.gt.hotel.web.service.TRoomCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 酒店ERP - 前台
 *
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:50
 */
@Api(tags = "酒店ERP 前台")
@Slf4j
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
        List<ErpRoomCategoryVo> roomList = roomCategoryService.findErpGroupRoomList(hotelId, categorySearch);
        return ResponseDTO.createBySuccess(roomList);
    }

    /**
     * 修改客房状态
     *
     * @param hotelId    酒店Id
     * @param roomId     客房ID
     * @param roomStatus 客房状态
     * @return ResponseDTO
     */
    @ApiOperation(value = "修改客房状态", notes = "修改客房状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelId", value = "酒店ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "roomId", value = "客房ID", required = true, paramType = "path"),
            @ApiImplicitParam(name = "roomStatus", value = "客房状态 1\t空房,2\t锁定,3\t在住,4\t清洁,5\t维护 ", required = false, paramType = "query")
    })
    @PatchMapping(value = "/{hotelId}/room/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO modifyRoomAvailability(@PathVariable("hotelId") Integer hotelId, @PathVariable("roomId") Integer roomId, @RequestParam("roomStatus") Integer roomStatus) {
        // 获取客房状态ID

        return null;
    }
}
