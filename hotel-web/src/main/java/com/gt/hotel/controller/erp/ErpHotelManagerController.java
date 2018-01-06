package com.gt.hotel.controller.erp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.Employee;
import com.gt.hotel.param.erp.AgreementParamter;
import com.gt.hotel.param.erp.PackageParamter;
import com.gt.hotel.util.JXCApiUtil;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.erp.AgreementOrganizationVo;
import com.gt.hotel.vo.erp.PackageRoomVo;
import com.gt.hotel.vo.erp.PackageVo;
import com.gt.hotel.vo.erp.ReceivablesVo;
import com.gt.hotel.web.service.TAgreementOrganizationService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TPackageRoomService;
import com.gt.hotel.web.service.TPackageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店ERP - 客户经理
 *
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:41
 */
@Api(tags = "酒店ERP 客户经理")
@RestController
@RequestMapping("/erp/manager")
public class ErpHotelManagerController extends BaseController {

    @Autowired
    private TAgreementOrganizationService agreementOrganizationService;

    @Autowired
    private TPackageService packageService;

    @Autowired
    private TPackageRoomService packageRoomService;
    
    @Autowired
    private WXMPApiUtil wxmpApiUtil;
    
    @Autowired
    private THotelService hotelService;
    
    @Autowired
    private JXCApiUtil jxcApiUtil;

    @ApiOperation(value = "协议单位or中介列表", notes = "协议单位or中介列表")
    @GetMapping(value = "organization/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<AgreementOrganizationVo>> organizationR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ModelAttribute AgreementParamter.AgreementQuery query, 
    		BindingResult bindingResult) {
    	invalidParameter(bindingResult);
        Page<AgreementOrganizationVo> page = agreementOrganizationService.erpQueryAgreementOrganization(hotelId, query);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "协议单位or中介 详情", notes = "协议单位or中介 详情")
    @GetMapping(value = "organization/{hotelId}/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<AgreementOrganizationVo> organizationR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("组织ID") @PathVariable("id") Integer id) {
    	return ResponseDTO.createBySuccess(agreementOrganizationService.erpQueryAgreementOrganizationDetail(id));
    }

    @ApiOperation(value = "单位or中介 收款查询 列表(含详情)", notes = "单位or中介 收款查询 列表(含详情)")
    @GetMapping(value = "organization/{hotelId}/receivables", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<ReceivablesVo>> receivablesR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ModelAttribute AgreementParamter.ReceivablesQuery query, 
    		BindingResult bindingResult) {
    	invalidParameter(bindingResult);
    	if(!StringUtils.isEmpty(query.getKeyword())) {
    		query.setKeyword("%" + query.getKeyword() + "%");
    	}
    	THotel hotel = hotelService.selectById(hotelId);
    	JSONObject result = wxmpApiUtil.getAllStaffShopId(hotel.getShopId(), null, null, null, null);
    	List<Employee> l = null;
        if ("0".equals(result.getString("code"))) {
            JSONObject temp = JSONObject.parseObject(result.getString("data"));
            l = JSONArray.parseArray(temp.getString("staffList"), Employee.class);
        }
    	Page<ReceivablesVo> page = agreementOrganizationService.erpQueryReceivables(hotelId, query);
    	List<ReceivablesVo> list = page.getRecords();
    	for(ReceivablesVo r : list) {
    		if(l != null) {
    			for(Employee e : l) {
    				if(r.getOperationId().equals(e.getId())) {
    					r.setOperationName(e.getName());
    				}
    			}
    		}
    	}
    	Long totalPrice = agreementOrganizationService.erpQueryReceivablesTotalPrice(hotelId);
    	totalPrice  = totalPrice == null ? 0 : totalPrice;
    	return ResponseDTO.createBySuccess(totalPrice + "", page);
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "编辑 协议单位or中介", notes = "编辑 协议单位or中介")
    @PostMapping(value = "organization/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO organizationCU(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@RequestBody AgreementParamter.AgreementInsert insert, 
    		BindingResult bindingResult, 
    		HttpServletRequest request) {
    	invalidParameter(bindingResult);
    	Integer busId = getLoginUser(request).getId();
		agreementOrganizationService.erpInsertAgreementOrganization(busId, hotelId, insert);
    	return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "删除 协议单位or中介", notes = "删除 协议单位or中介")
    @DeleteMapping(value = "organization/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO organizationD(
            @ApiParam("组织ID") @PathVariable("id") Integer id,
            HttpServletRequest request) {
        Integer busId = getLoginUser(request).getId();
        TAgreementOrganization a = new TAgreementOrganization();
        a.setMarkModified(CommonConst.DELETED);
        a.setUpdatedBy(busId);
        Wrapper<TAgreementOrganization> wrapper = new EntityWrapper<>();
        wrapper.eq("id", id);
        if (!agreementOrganizationService.update(a, wrapper)) {
            throw new ResponseEntityException(ResponseEnums.DELETE_ERROR);
        }
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "套餐 列表", notes = "套餐 列表")
    @GetMapping(value = "package/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<PackageVo>> packageR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ModelAttribute PackageParamter.PackageQuery query, 
    		BindingResult bindingResult) {
    	invalidParameter(bindingResult);
    	Page<PackageVo> page = packageService.erpQueryPackage(hotelId, query);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "套餐房间 列表", notes = "套餐房间 列表")
    @GetMapping(value = "package/{hotelId}/{packageId}/packageRoom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<List<PackageRoomVo>> packageRoomR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("组织ID") @PathVariable("packageId") Integer packageId) {
        return ResponseDTO.createBySuccess(packageRoomService.erpQueryPackageRoom(hotelId, packageId));
    }
    
    
}






















