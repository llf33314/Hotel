package com.gt.hotel.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.BusUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * API 参考类
 *
 * @author zhangmz
 * @create 2017/7/8
 */
@Controller
public class TestController extends BaseController {

    //    @Autowired
    //    private BusUserService busUserService;

    /**
     * json
     *
     * @param name
     *
     * @return
     */
    @ResponseBody
    @GetMapping( value = {"/", ""} )
    public ModelAndView hello(@RequestParam( defaultValue = " world,zhangmz!" ) String name, HttpSession session, ModelAndView mav) {
	System.out.println(session.getId());
	mav.setViewName("/nav.html");
	return mav;
    }

    /**
     * 跳转index 页面
     *
     * @param map
     *
     * @return
     */
    @ApiOperation( value = "首页", notes = "首页" )
    @GetMapping( "/index" )
    public ModelAndView index(ModelAndView map) {
	map.addObject("test", "hello zhangmz!");
	map.setViewName("index");
	this.logger.info("hahahah");
	//	this.logger.info( "你好嘛" );
	this.logger.info("不是吧");
	return map;
    }

    @GetMapping( "/test" )
    @ResponseBody
    public String haha() {
	this.logger.info("测试热部署");
	this.logger.info("22333");
	this.logger.info("230000000");
	return "你好嘛";
    }

    /**
     * 用户总数量
     *
     * @return String
     */
    @ApiOperation( value = "获取用户数量", notes = "模糊匹配手机号" )
    @ApiImplicitParam( name = "phone", value = "手机号码", paramType = "query", dataType = "long" )
    @ResponseBody
    @GetMapping( value = "/user/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public ResponseDTO userCount(Long phone) {
	this.logger.debug("phone is {}", phone);
	Wrapper< BusUser > busUserWrapper = null;
	if (phone != null) {
	    busUserWrapper = new EntityWrapper<>();
	    busUserWrapper.like("phone", phone.toString());
	}
	Integer count = /*this.busUserService.selectCount( busUserWrapper )*/0;
	return ResponseDTO.createBySuccess(count);
    }

    /**
     * 分页查询
     *
     * @param pageSize       页数
     * @param pageIndex      页码
     * @param searchKeyWords 关键字搜索 匹配 name & phone
     *
     * @return ResponseDTO
     */
    @ApiOperation( value = "手机号、姓名模糊查询", notes = "手机号、姓名模糊查询" )
    @ApiImplicitParams( {@ApiImplicitParam( name = "pageSize", value = "每页显示多少条数据", paramType = "query", required = false, dataType = "int", defaultValue = "10" ),
		    @ApiImplicitParam( name = "pageIndex", value = "当前页码", paramType = "query", required = false, dataType = "int", defaultValue = "1" ),
		    @ApiImplicitParam( name = "searchKeyWords", value = "用户姓名或手机号", paramType = "query", required = true, dataType = "String" )} )
    @ResponseBody
    @GetMapping( "/user" )
    public ResponseDTO findUsers(@RequestParam( defaultValue = "10" ) Integer pageSize, @RequestParam( defaultValue = "1" ) Integer pageIndex, String searchKeyWords) {
	this.logger.debug("searchKeyWords is {}", searchKeyWords);
	this.logger.debug("pageIndex is {}", pageIndex);
	this.logger.debug("pageSize is {}", pageSize);
	Page< BusUser > page = new Page<>(pageIndex, pageSize);
	Wrapper< BusUser > busUserWrapper = new EntityWrapper<>();
	busUserWrapper.like("phone", searchKeyWords);
	busUserWrapper.like("name", searchKeyWords);
	return ResponseDTO.createBySuccess( /*this.busUserService.selectPage( page, busUserWrapper )*/);
    }

    /**
     * 查询单用户信息
     *
     * @param uid 用户ID
     *
     * @return ResponseDTO
     */
    @ApiOperation( value = "用户ID 查询用户信息", notes = "查询用户信息" )
    @ApiImplicitParam( name = "uid", value = "用户ID", paramType = "path", required = true, dataType = "Integer" )
    @ResponseBody
    @GetMapping( "/user/{uid}" )
    public ResponseDTO findUser(@PathVariable Integer uid) {
	return ResponseDTO.createBySuccess( /*this.busUserService.findUser( uid )*/);
    }

}
