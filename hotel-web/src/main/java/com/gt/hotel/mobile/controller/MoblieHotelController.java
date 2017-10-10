<<<<<<< HEAD
package com.gt.hotel.mobile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gt.hotel.base.BaseController;

import io.swagger.annotations.Api;

@Api(description = "移动端")
@RestController
@RequestMapping("/mobile")
public class MoblieHotelController extends BaseController{
	
	
	
	
	
}
=======
package com.gt.hotel.mobile.controller;

import com.gt.hotel.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api( description = "移动端" )
@RestController
@RequestMapping( "/mobile" )
public class MoblieHotelController extends BaseController {

}
>>>>>>> e84f7e20e010edce8c294c517de8dd968a5742c6
