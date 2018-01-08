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

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.BusUser;
import com.gt.api.exception.SignException;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.entity.THotel;
import com.gt.hotel.entity.TPackage;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.other.MemberCard;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategory;
import com.gt.hotel.param.erp.AgreementParamter;
import com.gt.hotel.param.erp.HandingParam.HandingQuery;
import com.gt.hotel.param.erp.PackageParamter.PackageCU;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.RoomCategoryVo;
import com.gt.hotel.vo.erp.HandingStatisticsVo;
import com.gt.hotel.vo.erp.HandingVo;
import com.gt.hotel.vo.erp.ReceivablesVo;
import com.gt.hotel.web.service.TAgreementOrganizationService;
import com.gt.hotel.web.service.THotelService;
import com.gt.hotel.web.service.TPackageService;
import com.gt.hotel.web.service.TRoomCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 酒店ERP - 财务
 *
 * @author Reverien9@gmail.com
 * 2017年11月21日 上午10:52:29
 */
@Api(tags = "酒店ERP 财务")
@RestController
@RequestMapping("/erp/finance")
public class ErpHotelFinanceController extends BaseController {

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    @Autowired
    private THotelService hotelService;
    
    @Autowired
    private TAgreementOrganizationService agreementOrganizationService;

    @Autowired
    private TPackageService packageService;

    @Autowired
    private TRoomCategoryService roomCategoryService;
    
    @ApiOperation(value = "财务 - 已完成订单(含详情)", notes = "财务 - 已完成订单(含详情)")
    @GetMapping(value = "{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<ReceivablesVo>> financeR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ModelAttribute AgreementParamter.ReceivablesQuery query, 
    		BindingResult bindingResult) {
    	invalidParameter(bindingResult);
    	if(!StringUtils.isEmpty(query.getKeyword())) {
    		query.setKeyword("%" + query.getKeyword() + "%");
    	}
    	Page<ReceivablesVo> page = agreementOrganizationService.erpQueryReceivables(hotelId, query);
    	return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "财务 - 已完成订单 详情 会员信息", notes = "财务 - 已完成订单 详情 会员信息")
    @GetMapping(value = "{hotelId}/member/{memberId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<MemberCard> financeDetailMember(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("会员ID") @PathVariable("memberId") Integer memberId,
    		HttpServletRequest request) {
    	THotel hotel = hotelService.selectById(hotelId);
    	MemberCard memberCard = null;
    	try {
    		JSONObject member = wxmpApiUtil.findMemberByids(memberId, hotel.getBusId());
    		JSONObject json = wxmpApiUtil.findMemberCard(member.getString("phone"), hotel.getBusId(), hotel.getShopId());
    		if(json != null && json.getInteger("code").equals(0)) {
    			memberCard = JSONObject.toJavaObject(json.getJSONObject("data"), MemberCard.class);
    		}
		} catch (SignException e) {
			throw new ResponseEntityException(ResponseEnums.FAILED_TO_OBTAIN_MEMBER_INFORMATION);
		}
        return ResponseDTO.createBySuccess(memberCard);
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "协议单位or中介 - 通过", notes = "协议单位or中介 - 通过")
    @PostMapping(value = "{hotelId}/organizationPass/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO organizationPass(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("协议单位or中介ID") @PathVariable("id") Integer id,
    		HttpServletRequest request) {
    	BusUser user = getLoginUser(request);
		TAgreementOrganization ao = new TAgreementOrganization();
		ao.setStatus(CommonConst.PASSED);
		ao.setUpdatedBy(user.getId());
		Wrapper<TAgreementOrganization> aw = new EntityWrapper<>();
		aw.eq("id", id).eq("hotel_id", hotelId);
		if(!agreementOrganizationService.update(ao, aw)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.SAVE_ERROR.getMsg());
		}
    	return ResponseDTO.createBySuccess();
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "协议单位or中介 - 驳回/取消", notes = "协议单位or中介 - 驳回/取消")
    @PostMapping(value = "{hotelId}/organizationReject/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO organizationReject(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@ApiParam("协议单位or中介ID") @PathVariable("id") Integer id,
    		@ApiParam("驳回原因") String rejectedReason,
    		HttpServletRequest request) {
    	BusUser user = getLoginUser(request);
    	TAgreementOrganization ao = new TAgreementOrganization();
    	ao.setStatus(CommonConst.NOT_PASSED);
    	ao.setRejectedReason(rejectedReason);
    	ao.setUpdatedBy(user.getId());
    	Wrapper<TAgreementOrganization> aw = new EntityWrapper<>();
    	aw.eq("id", id).eq("hotel_id", hotelId);
    	if(!agreementOrganizationService.update(ao, aw)) {
    		return ResponseDTO.createByErrorMessage(ResponseEnums.SAVE_ERROR.getMsg());
    	}
    	return ResponseDTO.createBySuccess();
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "设置 - 套餐 - 新增/编辑", notes = "设置 - 套餐 - 新增/编辑")
    @PostMapping(value = "{hotelId}/package", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO packageCU(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@RequestBody @ApiParam("套餐参数") PackageCU packageCU,
    		HttpServletRequest request) {
    	BusUser user = getLoginUser(request);
    	packageCU.setHotelId(hotelId);
    	packageService.erpPackageCU(user, packageCU);
    	return ResponseDTO.createBySuccess();
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "设置 - 套餐 - 删除", notes = "设置 - 套餐 - 删除")
    @DeleteMapping(value = "{hotelId}/package", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO packageD(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId,
    		@RequestBody @ApiParam("ID集合") List<Integer> ids,
    		HttpServletRequest request) {
    	BusUser user = getLoginUser(request);
    	Wrapper<TPackage> pw = new EntityWrapper<>();
    	pw.in("id", ids);
		TPackage p = new TPackage();
		p.setMarkModified(CommonConst.DELETED);
		p.setUpdatedBy(user.getId());
		if(!packageService.update(p, pw)) {
			return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
		}
    	return ResponseDTO.createBySuccess();
    }
    
    @ApiOperation(value = "设置 - 套餐 - 房型列表", notes = "设置 - 套餐 - 房型列表")
    @GetMapping(value = "{hotelId}/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<RoomCategoryVo>> categoryR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId) {
		QueryRoomCategory param = new QueryRoomCategory();
		param.setHotelId(hotelId);
		param.setPageSize(999999);
		Page<RoomCategoryVo> page = roomCategoryService.queryRoomCategory(param);
    	return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "挂账管理 - 挂账列表", notes = "挂账管理 - 挂账列表")
    @GetMapping(value = "{hotelId}/hanging", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HandingVo>> hangingR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId, 
    		@RequestBody @ApiParam("参数") HandingQuery param) {
    	if(!StringUtils.isEmpty(param.getKeyword())) {
    		param.setKeyword("%" + param.getKeyword() + "%");
    	}
		Page<HandingVo> page = agreementOrganizationService.erpQueryHandingList(hotelId, param);
    	return ResponseDTO.createBySuccess(page);
    }
    
    @ApiOperation(value = "挂账管理 - 挂账列表 - 统计", notes = "挂账管理 - 挂账列表 - 统计")
    @GetMapping(value = "{hotelId}/hangingStatistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<HandingStatisticsVo> hangingStatisticsR(
    		@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId) {
    	HandingStatisticsVo hs = agreementOrganizationService.erpQueryHandingStatistics(hotelId);
    	return ResponseDTO.createBySuccess(hs);
    }
    
}
