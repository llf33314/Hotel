package com.gt.hotel.controller.back;

import com.gt.hotel.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "酒店后台-订单管理")
@RestController
@RequestMapping("/back")
public class HotelOrderController extends BaseController {

}
