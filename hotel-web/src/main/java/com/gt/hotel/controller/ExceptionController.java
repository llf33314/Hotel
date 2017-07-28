package com.gt.hotel.controller;

import com.gt.hotel.exception.ResponseEntityException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangmz
 * @version 1.0.0
 * @date 2017/07/28
 */
@Controller
public class ExceptionController {

    @GetMapping( "/400" )
    public String ex400() throws Exception {
	throw new Exception( "出错啦" );
	//        return "";
    }

    @GetMapping( "/500" )
    @ResponseBody
    public String ex500() throws ResponseEntityException {
	throw new ResponseEntityException( "出错啦" );
    }

}
