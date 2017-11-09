package com.gt.hotel.controller.back;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.BusUser;
import com.gt.api.bean.session.Member;
import com.gt.api.exception.SignException;
import com.gt.api.util.SessionUtils;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TAuthorization;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.Employee;
import com.gt.hotel.param.ERPParameter;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.param.RoomParameter;
import com.gt.hotel.param.WXMPParameter;
import com.gt.hotel.util.QrCodeUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.AuthorizationVo;
import com.gt.hotel.vo.HotelVo;
import com.gt.hotel.vo.RoomPermanentVo;
import com.gt.hotel.vo.SysDictionaryVo;
import com.gt.hotel.web.service.SysDictionaryService;
import com.gt.hotel.web.service.TAuthorizationService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店后台-ERP设置
 *
 * @author Reverien9@gmail.com
 * 2017年10月25日 下午12:03:40
 */
@Api(tags = "酒店后台-ERP设置")
@RestController
@RequestMapping("/back/erp")
public class HotelErpSetController extends BaseController {

    @Autowired
    THotelService tHotelService;

    @Autowired
    TRoomCategoryService tRoomCategoryService;

    @Autowired
    SysDictionaryService sysDictionaryService;

    @Autowired
    TAuthorizationService tAuthorizationService;

    @Autowired
    WXMPApiUtil WXMPApiUtil;

    @ApiOperation(value = "查询 酒店ERP对象", notes = "查询酒店ERP对象")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<HotelVo> erpSettingR(@Param("酒店ID") @PathVariable("hotelId") Integer hotelId) {
        HotelVo h = tHotelService.queryHotelERP(hotelId);
        return ResponseDTO.createBySuccess(h);
    }

    @ApiOperation(value = "保存 ERP前台设置", notes = "保存 ERP前台设置")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO erpSettingCU(@Validated @Param("参数") @RequestBody ERPParameter.ERPSave save, BindingResult bindingResult, HttpSession session) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Integer busid = getLoginUserId(session);
        tHotelService.SaveHotelERP(busid, save);
        return ResponseDTO.createBySuccess();
    }

    //////////////////////////////////////////////////////////// 长包房 //////////////////////////////////////////////////////////

    @ApiOperation(value = "保存 长包房设置", notes = "保存 长包房设置")
    @PostMapping(value = "{hotelId}/roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomPermanentCU(@Valid @Param("参数") @RequestBody RoomParameter.RoomPermanent per, 
    		@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, BindingResult bindingResult, HttpSession session) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Integer busId = getLoginUserId(session);
        tRoomCategoryService.SaveRoomPermanent(busId, per);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "查询 长包房设置", notes = "查询 长包房设置")
    @GetMapping(value = "{hotelId}/roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<RoomPermanentVo>> roomPermanentR(@Validated @Param("参数") @ModelAttribute RoomParameter.RoomPermanentQuery param, 
    		@Param("酒店ID") @PathVariable("hotelId") Integer hotelId) {
        Page<RoomPermanentVo> page = tRoomCategoryService.queryRoomPermanent(hotelId, param);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "删除 长包房设置", notes = "删除 长包房设置")
    @DeleteMapping(value = "{hotelId}/roomPermanent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO roomPermanentD(@RequestBody @ApiParam("长包房设置ID 数组") List<Integer> ids, 
    		@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, HttpSession session) {
        Integer busId = getLoginUserId(session);
        tRoomCategoryService.delRoomPermanent(busId, ids);
        return ResponseDTO.createBySuccess();
    }

    ////////////////////////////////////////////////////////////权限设置 //////////////////////////////////////////////////////////

    @ApiOperation(value = "查询 权限功能列表", notes = "查询 权限功能列表")
    @GetMapping(value = "function", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<SysDictionaryVo>> functionR(HotelPage param) {
        Page<SysDictionaryVo> page = sysDictionaryService.queryDictionary(CommonConst.DICT_FUNCTION, param);
        return ResponseDTO.createBySuccess(page);
    }

    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询 员工列表", notes = "查询 员工列表")
    @GetMapping(value = "employee/{shopId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<Employee>> employeeR(@PathVariable("shopId") Integer shopId, 
    		@Param("门店ID") @ModelAttribute WXMPParameter.queryEmployee qe) {
    	Page<Employee> page = qe.initPage();
//        JSONObject result = WXMPApiUtil.getAllStaffShopId(shopId, qe.getName(), qe.getPhone(), qe.getPage(), qe.getPageSize());
        JSONObject result = WXMPApiUtil.getAllStaffShopId(shopId, qe.getName(), qe.getPhone(), null, null);
        if ("0".equals(result.getString("code"))) {
            JSONObject temp = JSONObject.parseObject(result.getString("data"));
            List<Employee> l = JSONArray.parseArray(temp.getString("staffList"), Employee.class);
            List<Employee> le = new ArrayList<>();
            if(!StringUtils.isEmpty(qe.getKeyword())) {
            	for(int i=0;i<l.size();i++) {
            		if(l.get(i).getName().indexOf(qe.getKeyword().trim()) != -1 
            				|| l.get(i).getPhone().indexOf(qe.getKeyword().trim()) != -1) {
            			le.add(l.get(i));
            		}
            	}
            }else {
            	le = l;
            }
            List<Employee> les = new ArrayList<>();
            int begin = (qe.getPage()-1)*qe.getPageSize();
            int end = begin + qe.getPageSize();
            if(le.size() > begin) {
            	for(int i=begin;i<end;i++) {
            		if((le.size()-1) >= i) {
            			les.add(le.get(i));
            		}else {
            			break;
            		}
            	}
            }else {
            	les = le;
            }
            page.setRecords(les);
            page.setTotal(temp.getInteger("count"));
        } else {
        	throw new ResponseEntityException(result.getString("msg"));
        }
        return ResponseDTO.createBySuccess(page);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "查询 授权管理列表", notes = "查询 授权管理列表")
    @GetMapping(value = "{hotelId}/author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<AuthorizationVo>> authorR(@Validated @ModelAttribute ERPParameter.AuthorQuery param, 
    		 BindingResult bindingResult, @Param("酒店ID") @PathVariable("hotelId") Integer hotelId) {
    	ResponseDTO msg = InvalidParameterII(bindingResult);
        if(msg != null) {
        	return msg;
        }
        Page<AuthorizationVo> page = tAuthorizationService.queryAuthor(hotelId, param);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "新增 授权管理", notes = "新增 授权管理")
    @PostMapping(value = "{hotelId}/author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO authorDCU(@RequestBody @ApiParam("授权管理ID 数组") List<ERPParameter.AuthorSave> authors, 
    		@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, HttpSession session) {
        Integer busid = getLoginUserId(session);
        tAuthorizationService.saveAuthor(busid, authors);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "删除 授权管理", notes = "删除 授权管理")
    @DeleteMapping(value = "{hotelId}/author", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO authorD(@RequestBody @ApiParam("授权管理ID 数组") List<Integer> ids, 
    		@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, HttpSession session) {
        Integer busId = getLoginUserId(session);
        tAuthorizationService.delAuthor(busId, ids);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "开启授权", notes = "开启授权")
    @GetMapping(value = "{hotelId}/author/{authorId}/open", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void authorOpen(@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, 
    		@Param("授权ID") @PathVariable("authorId") Integer authorId, HttpServletResponse response, HttpServletRequest request) {
    	OutputStream outputStream;
		try {
			outputStream = new BufferedOutputStream(response.getOutputStream());
			BufferedImage bi = QrCodeUtil.encode(getHost(request) + "/78CDF1/" + hotelId + "/" + authorId, 
					null, "H", null, outputStream, 500, 500, 1);
			ImageIO.write(bi, "png", outputStream);
			outputStream.flush();  
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @ApiOperation(value = "首页", notes = "首页")
    @GetMapping(value = "78CDF1/{hotelId}/{authorId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView moblieHome(HttpServletRequest request, @Param("酒店ID") @PathVariable("hotelId") Integer hotelId, 
    		@Param("授权ID") @PathVariable("authorId") Integer authorId, ModelAndView model) {
    	Integer busId = tHotelService.selectById(hotelId).getBusId();
    	Member member = SessionUtils.getLoginMember(request, busId);
    	if(StringUtils.isEmpty(member) || StringUtils.isEmpty(member.getId())) {
    		Map<String, Object> param = new HashMap<>();
    		param.put("busId", busId);
    		param.put("requestUrl", getHost(request) + "/78CDF1/" + hotelId + "/" + authorId);
    		String url = null;
			try {
				url = authorizeMember(request, param);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		if(!StringUtils.isEmpty(url)) {
    			model.setViewName(url);
    	        return model;
    		}
    	}
    	Wrapper<TAuthorization> wrapper = new EntityWrapper<>();
    	wrapper.eq("id", authorId);
		TAuthorization entity = new TAuthorization();
		entity.setScanCodeAuthorization(CommonConst.AUTHORIZED_ENABLED);
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(busId);
		entity.setMemberId(member.getId());
		if(tAuthorizationService.update(entity, wrapper)) {
			try {
				WXMPApiUtil.getSocketApi("hotel:backsocket", null, "success");
			} catch (SignException e) {
				e.printStackTrace();
			}
			model.setViewName("/author/success.html");
		}else {
			model.setViewName("/author/fail.html");
		}
        return model;
    }
    
    @ApiOperation(value = "关闭授权", notes = "关闭授权")
    @PostMapping(value = "{hotelId}/author/{authorId}/close", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO authorClose(@Param("酒店ID") @PathVariable("hotelId") Integer hotelId, 
    		@Param("授权ID") @PathVariable("authorId") Integer authorId, HttpServletRequest request) {
    	BusUser busUser = getLoginUserId(request);
    	Wrapper<TAuthorization> wrapper = new EntityWrapper<>();
    	wrapper.eq("id", authorId);
		TAuthorization entity = new TAuthorization();
		entity.setScanCodeAuthorization(CommonConst.AUTHORIZED_UNENABLED);
		entity.setUpdatedAt(new Date());
		entity.setUpdatedBy(busUser.getId());
		if(tAuthorizationService.update(entity, wrapper)) {
			return ResponseDTO.createBySuccess();
		}else {
			return ResponseDTO.createByError();
		}
    }
}
