package com.gt.hotel.controller.erp;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TAgreementOrganization;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.AgreementParamter;
import com.gt.hotel.param.PackageParamter;
import com.gt.hotel.vo.AgreementOrganizationVo;
import com.gt.hotel.vo.PackageRoomVo;
import com.gt.hotel.vo.PackageVo;
import com.gt.hotel.web.service.TAgreementOrganizationService;
import com.gt.hotel.web.service.TPackageRoomService;
import com.gt.hotel.web.service.TPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    TAgreementOrganizationService agreementOrganizationService;

    @Autowired
    TPackageService packageService;

    @Autowired
    TPackageRoomService packageRoomService;

    @ApiOperation(value = "协议单位or中介列表", notes = "协议单位or中介列表")
    @GetMapping(value = "organization/{hotelId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<AgreementOrganizationVo>> organizationR(
            @ApiParam("门店ID") @PathVariable("hotelId") Integer hotelId,
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
    public ResponseDTO<Page<PackageVo>> packageR(@ApiParam("酒店ID") @PathVariable("hotelId") Integer hotelId, @ModelAttribute PackageParamter.PackageQuery query,
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






















