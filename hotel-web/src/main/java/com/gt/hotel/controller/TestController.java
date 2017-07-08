package com.gt.hotel.controller;

import com.gt.hotel.dao.BusUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhangmz
 * @create 2017/7/8
 */
@Controller
public class TestController {

    @Autowired
    private BusUserDao busUserDao;

    /**
     * json
     * @param name
     * @return
     */
    @RequestMapping(value = {"/",""})
    @ResponseBody
    public String hello(@RequestParam(defaultValue = " world,zhangmz!")String name){
        return "hello "+name;
    }

    /**
     * 跳转index 页面
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView map){
        map.addObject("test","hello zhangmz!");
        map.setViewName("index");
        return map;
    }

    @ResponseBody
    @RequestMapping("/user/count")
    public String userCount() {
        Integer count = this.busUserDao.selectCount(null);
        return "用户数量：" + count;
    }


}
