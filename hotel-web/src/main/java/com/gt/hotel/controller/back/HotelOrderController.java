package com.gt.hotel.controller.back;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gt.api.bean.session.WxPublicUsers;
import com.gt.api.exception.SignException;
import com.gt.api.util.KeysUtil;
import com.gt.api.util.SessionUtils;
import com.gt.entityBo.ErpRefundBo;
import com.gt.hotel.base.BaseController;
import com.gt.hotel.constant.CommonConst;
import com.gt.hotel.dto.ResponseDTO;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.entity.TOrderRoom;
import com.gt.hotel.enums.ResponseEnums;
import com.gt.hotel.exception.ResponseEntityException;
import com.gt.hotel.param.HotelOrderParameter;
import com.gt.hotel.param.RoomCategoryParameter.QueryRoomCategoryOne;
import com.gt.hotel.param.RoomMobileParameter;
import com.gt.hotel.util.WXMPApiUtil;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import com.gt.hotel.vo.RoomOrderPriceVO;
import com.gt.hotel.web.service.TOrderRoomService;
import com.gt.hotel.web.service.TOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 酒店后台-订单管理
 *
 * @author Reverien9@gmail.com 2017年10月25日 下午12:04:02
 */
@Api(tags = "酒店后台-订单管理")
@Slf4j
@RestController
@RequestMapping("/back/order")
public class HotelOrderController extends BaseController {

    @Autowired
    TOrderService tOrderService;
    
    @Autowired
    TOrderRoomService orderRoomService;

    @Autowired
    private WXMPApiUtil wxmpApiUtil;

    private long midflag = 0;
    
    @ApiOperation(value = "删除 房间&餐饮订单(共用)", notes = "删除 房间&餐饮订单(共用)")
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @SuppressWarnings("rawtypes")
    public ResponseDTO orderD(@RequestBody @ApiParam("订单ID 数组") List<Integer> ids, HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        TOrder h = new TOrder();
        h.setMarkModified(CommonConst.DELETED);
        h.setUpdatedAt(new Date());
        h.setUpdatedBy(busid);
        tOrderService.update(h, wrapper);
        return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "订单  确认 操作", notes = "订单  确认 操作")
    @PostMapping(value = "{orderId}/confirm", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO orderConfirm(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        TOrder order = tOrderService.selectById(orderId);
        if (!order.getOrderStatus().equals(CommonConst.ORDER_PROCESSING)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
        }
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("id", orderId);
        TOrder newOrder = new TOrder();
        newOrder.setOrderStatus(CommonConst.ORDER_CONFIRMED);
        newOrder.setUpdatedBy(busid);
        newOrder.setUpdatedAt(new Date());
        if (!tOrderService.update(newOrder, wrapper)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.SAVE_ERROR.getMsg());
        }
        return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "订单  取消 操作", notes = "订单  取消 操作")
    @PostMapping(value = "{orderId}/cancel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO orderCancel(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        TOrder order = tOrderService.selectById(orderId);
        if (!order.getOrderStatus().equals(CommonConst.ORDER_PROCESSING) && !order.getOrderStatus().equals(CommonConst.ORDER_CONFIRMED)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
        }
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("id", orderId);
        TOrder newOrder = new TOrder();
        newOrder.setOrderStatus(CommonConst.ORDER_CANCALLED);
        newOrder.setUpdatedBy(busid);
        newOrder.setUpdatedAt(new Date());
        if (!tOrderService.update(newOrder, wrapper)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
        }
        return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "订单  完成 操作", notes = "订单  完成 操作")
    @PostMapping(value = "{orderId}/complete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO foodOrderComplete(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId, HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        TOrder order = tOrderService.selectById(orderId);
        if (!(order.getOrderStatus().equals(CommonConst.ORDER_CONFIRMED)
                || order.getOrderStatus().equals(CommonConst.ORDER_CANCALLED))) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
        }
        Wrapper<TOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("id", orderId);
        TOrder newOrder = new TOrder();
        newOrder.setOrderStatus(CommonConst.ORDER_COMPLETED);
        newOrder.setUpdatedBy(busid);
        newOrder.setUpdatedAt(new Date());
        if (!tOrderService.update(newOrder, wrapper)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
        }
        tOrderService.orderComplete(orderId, busid);
        return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings({ "rawtypes", "static-access" })
    @ApiOperation(value = "订单  退款 操作", notes = "订单  退款 操作(占位)")
    @PostMapping(value = "{orderId}/refunds", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO orderRefunds(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId,
                                    HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        TOrder order = tOrderService.selectById(orderId);
        if (!(order.getPayStatus().equals(CommonConst.PAY_STATUS_PAID) && order.getOrderStatus().equals(CommonConst.ORDER_CANCALLED))) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.PAY_STATUS_ERROR.getMsg());
        }
        try {
            WxPublicUsers publicUser = SessionUtils.getLoginPbUser(request);
            //支付宝
            if (order.getPayType().equals(CommonConst.PAY_TYPE_ALI)) {
                Map<String, Object> params = new HashMap<>();
                params.put("out_trade_no", order.getOrderNum());
                params.put("busId", order.getBusId());
                params.put("desc", "酒店后台退款");
                params.put("fee", order.getRealPrice() / 100d);
                params.put("notifyUrl", getHost(request) + "/back/order/" + orderId + "/aliPayCallBack");
                KeysUtil keysUtil = new KeysUtil();
                String key = keysUtil.getEncString(JSONObject.toJSONString(params));
                System.err.println(params.toString());
                System.err.println(key);
                return ResponseDTO.createBySuccess(key);
                //微信
            } else if (order.getPayType().equals(CommonConst.PAY_TYPE_WX)) {
                JSONObject result = wxmpApiUtil.wxmemberPayRefund(publicUser.getAppid(), publicUser.getMchId(), order.getOrderNum(),
                        order.getRealPrice() / 100d, order.getRealPrice() / 100d);
                if (result.getInteger("code").equals(0) || result.getString("msg").equals("订单已全额退款")) {
//                    ErpRefundBo bo = new ErpRefundBo();
//                    bo.setBusId(order.getBusId());
//                    bo.setOrderCode(order.getOrderNum());
//                    // ali = 0, wx = 1, 储值卡 = 5
//                    bo.setRefundPayType((CommonConst.PAY_TYPE_VALUE_CARD + 2));
//                    bo.setRefundMoney(0d);
//                    bo.setRefundJifen(order.getIntegral());
//                    bo.setRefundFenbi(order.getFb() / 100d);
//                    bo.setRefundDate(System.currentTimeMillis());
//                    wxmpApiUtil.memberRefundErp(bo);
                    Wrapper<TOrder> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", orderId);
                    TOrder newOrder = new TOrder();
                    newOrder.setPayStatus(CommonConst.PAY_STATUS_REFUNDS);
                    newOrder.setRefundAmount(order.getRealPrice());
                    newOrder.setUpdatedBy(busid);
                    if (!tOrderService.update(newOrder, wrapper)) {
                        return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
                    } else {
                        return ResponseDTO.createBySuccess();
                    }
                } else {
                    return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
                }
                //储蓄卡
            } else if (order.getPayType().equals(CommonConst.PAY_TYPE_VALUE_CARD)) {
                ErpRefundBo bo = new ErpRefundBo();
                bo.setBusId(order.getBusId());
                bo.setOrderCode(order.getOrderNum());
                // ali = 0, wx = 1, 储值卡 = 5
                bo.setRefundPayType((CommonConst.PAY_TYPE_VALUE_CARD + 2));
                bo.setRefundMoney(order.getRealPrice() / 100d);
                bo.setRefundJifen(order.getIntegral());
                bo.setRefundFenbi(order.getFb() / 100d);
                bo.setRefundDate(System.currentTimeMillis());
                JSONObject result = wxmpApiUtil.memberRefundErp(bo);
                if (result.getInteger("code").equals(0)) {
                    Wrapper<TOrder> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", orderId);
                    TOrder newOrder = new TOrder();
                    newOrder.setPayStatus(CommonConst.PAY_STATUS_REFUNDS);
                    newOrder.setRefundAmount(order.getRealPrice());
                    newOrder.setUpdatedBy(busid);
                    if (!tOrderService.update(newOrder, wrapper)) {
                        return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
                    } else {
                        return ResponseDTO.createBySuccess();
                    }
                } else {
                    return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
        }
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "支付宝  退款 回调", notes = "支付宝  退款 回调", hidden = true)
    @PostMapping(value = "{orderId}/aliPayCallBack", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void aliPayCallBack(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId,
    		@RequestBody Map<String, Object> params,
    		HttpServletRequest request) {
        TOrder order = tOrderService.selectById(orderId);
        System.err.println(order);
        try {
            if (params.get("outTradeNo") != null && params.get("outTradeNo").toString().trim().length() > 0) {
//                ErpRefundBo bo = new ErpRefundBo();
//                bo.setBusId(order.getBusId());
//                bo.setOrderCode(order.getOrderNum());
//                // ali = 0, wx = 1, 储值卡 = 5
//                bo.setRefundPayType((CommonConst.PAY_TYPE_VALUE_CARD + 2));
//                bo.setRefundMoney(order.getRealPrice() / 100d);
//                bo.setRefundJifen(order.getIntegral());
//                bo.setRefundFenbi(order.getFb() / 100d);
//                bo.setRefundDate(System.currentTimeMillis());
//                JSONObject result = wxmpApiUtil.memberRefundErp(bo);
//                if (result.getInteger("code").equals(0)) {
                    Wrapper<TOrder> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", orderId);
                    TOrder newOrder = new TOrder();
                    newOrder.setPayStatus(CommonConst.PAY_STATUS_REFUNDS);
                    newOrder.setRefundAmount(order.getRealPrice());
                    if (tOrderService.update(newOrder, wrapper)) {
                        wxmpApiUtil.getSocketApi("hotel:socket", null, "success");
                    }
//                }
            }
        } catch (SignException e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////↓房间↓ //////////////////////////////////////////////////////////

    @ApiOperation(value = "房间订单列表", notes = "房间订单列表")
    @GetMapping(value = "room", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackRoomOrderVo>> roomOrderR(HotelOrderParameter.RoomOrderQuery param,
                                                              HttpServletRequest request) {
        Integer busId = getLoginUser(request).getId();
        if(param.getKeyword() != null) {
        	param.setPage(1);
        }
        Page<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrder(busId, param);
        return ResponseDTO.createBySuccess(page);
    }

    @ApiOperation(value = "房间订单详情", notes = "房间订单详情")
    @GetMapping(value = "room/{orderId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<HotelBackRoomOrderVo> roomOrderOneR(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId) {
        HotelBackRoomOrderVo order = tOrderService.queryRoomOrderOne(orderId);
        return ResponseDTO.createBySuccess(order);
    }

    @SuppressWarnings({"rawtypes"})
    @ApiOperation(value = "添加线下订单", notes = "添加线下订单")
    @PostMapping(value = "AddOffLineOrder", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO addOffLineOrder(@Validated @RequestBody HotelOrderParameter.OffLineOrder order, BindingResult bindingResult, HttpServletRequest request) {
        ResponseDTO msg = invalidParameterII(bindingResult);
        if (msg != null) {
            return msg;
        }
        Integer busId = getLoginUser(request).getId();
        tOrderService.addOffLineOrder(busId, order);
        return ResponseDTO.createBySuccess();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @ApiOperation(value = "房间订单入住", notes = "房间订单入住")
    @PostMapping(value = "{orderId}/checkIn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<QueryRoomCategoryOne> checkIn(
    		@PathVariable("orderId") Integer orderId, 
    		@Validated @RequestBody HotelOrderParameter.CheckInParam param, 
    		BindingResult bindingResult, 
    		HttpServletRequest request) {
        ResponseDTO msg = invalidParameterII(bindingResult);
        if (msg != null) {
            return msg;
        }
        TOrder order = tOrderService.selectById(orderId);
        if (!order.getOrderStatus().equals(CommonConst.ORDER_CONFIRMED)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.ORDER_STATUS_ERROR.getMsg());
        }
        Integer busid = getLoginUser(request).getId();
        tOrderService.checkIn(busid, orderId, param);
        return ResponseDTO.createBySuccess();
    }

    @ApiOperation(value = "房间订单  结账退房 操作", notes = "房间订单  结账退房 操作")
    @PostMapping(value = "{orderId}/checkOut", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<String> checkOut(@ApiParam("订单ID") @PathVariable("orderId") Integer orderId,
                                        @RequestBody HotelOrderParameter.RefundsParam refundsP,
                                        HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        TOrder order = tOrderService.selectById(orderId);
        Wrapper<TOrderRoom> w = new EntityWrapper<>();
        w.eq("order_id", orderId);
		TOrderRoom orderRoom = orderRoomService.selectOne(w);
        if (!order.getOrderStatus().equals(CommonConst.ORDER_CHECK_IN) && 
        		!order.getPayStatus().equals(CommonConst.PAY_STATUS_PAID) && 
        		!order.getPayType().equals(CommonConst.PAY_TYPE_OFFLINE)) {
            return ResponseDTO.createByErrorMessage(ResponseEnums.PAY_STATUS_ERROR.getMsg());
        }
        try {
            WxPublicUsers publicUser = SessionUtils.getLoginPbUser(request);
            //线下订单
            if((orderRoom != null && orderRoom.getOrderFrom().equals(1)) || order.getRealPrice() == 0 || order.getPayType().equals(2)) {
            	Wrapper<TOrder> wrapper = new EntityWrapper<>();
                wrapper.eq("id", orderId);
                TOrder newOrder = new TOrder();
                newOrder.setOrderStatus(CommonConst.PAY_STATUS_REFUNDS);
                newOrder.setUpdatedBy(busid);
                newOrder.setRefundAmount(refundsP.getRefundFee() == null ? 0 : refundsP.getRefundFee());
                newOrder.setRefundReason(refundsP.getRefundReason());
                if (!tOrderService.update(newOrder, wrapper)) {
                    return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
                } else {
                    return ResponseDTO.createBySuccess();
                }
            }
            //支付宝
            if (order.getPayType().equals(CommonConst.PAY_TYPE_ALI)) {
                JSONObject params = new JSONObject();
                params.put("out_trade_no", order.getOrderNum());
                params.put("busId", order.getBusId());
                params.put("desc", "酒店后台退款");
                params.put("fee", refundsP.getRefundFee() / 100d);
                params.put("notifyUrl", getHost(request) + "/back/order" + orderId + "/aliPayCallBack");
                String key = KeysUtil.getEncString(params.toJSONString());
                return ResponseDTO.createBySuccess(key);
                //微信
            } else if (order.getPayType().equals(CommonConst.PAY_TYPE_WX)) {
                JSONObject result = wxmpApiUtil.wxmemberPayRefund(publicUser.getAppid(), publicUser.getMchId(), order.getOrderNum(),
                        refundsP.getRefundFee() / 100d, order.getRealPrice() / 100d);
                if (result.getInteger("code").equals(0)) {
                    ErpRefundBo bo = new ErpRefundBo();
                    bo.setBusId(order.getBusId());
                    bo.setOrderCode(order.getOrderNum());
                    // ali = 0, wx = 1, 储值卡 = 5
                    bo.setRefundPayType((CommonConst.PAY_TYPE_VALUE_CARD + 2));
                    bo.setRefundMoney(0d);
                    bo.setRefundJifen(order.getIntegral());
                    bo.setRefundFenbi(order.getFb() / 100d);
                    bo.setRefundDate(System.currentTimeMillis());
                    wxmpApiUtil.memberRefundErp(bo);
                    Wrapper<TOrder> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", orderId);
                    TOrder newOrder = new TOrder();
                    newOrder.setOrderStatus(CommonConst.PAY_STATUS_REFUNDS);
                    newOrder.setUpdatedBy(busid);
                    newOrder.setRefundAmount(refundsP.getRefundFee());
                    newOrder.setRefundReason(refundsP.getRefundReason());
                    if (!tOrderService.update(newOrder, wrapper)) {
                        return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
                    } else {
                        return ResponseDTO.createBySuccess();
                    }
                } else {
                    return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
                }
                //储蓄卡
            } else if (order.getPayType().equals(CommonConst.PAY_TYPE_VALUE_CARD)) {
                ErpRefundBo bo = new ErpRefundBo();
                bo.setBusId(order.getBusId());
                bo.setOrderCode(order.getOrderNum());
                // ali = 0, wx = 1, 储值卡 = 5
                bo.setRefundPayType((CommonConst.PAY_TYPE_VALUE_CARD + 2));
                bo.setRefundMoney(refundsP.getRefundFee() / 100d);
                bo.setRefundJifen(order.getIntegral());
                bo.setRefundFenbi(order.getFb() / 100d);
                bo.setRefundDate(System.currentTimeMillis());
                JSONObject result = wxmpApiUtil.memberRefundErp(bo);
                if (result.getInteger("code").equals(0)) {
                    Wrapper<TOrder> wrapper = new EntityWrapper<>();
                    wrapper.eq("id", orderId);
                    TOrder newOrder = new TOrder();
                    newOrder.setOrderStatus(CommonConst.PAY_STATUS_REFUNDS);
                    newOrder.setUpdatedBy(busid);
                    newOrder.setRefundAmount(refundsP.getRefundFee());
                    if (!tOrderService.update(newOrder, wrapper)) {
                        return ResponseDTO.createByErrorMessage(ResponseEnums.OPERATING_ERROR.getMsg());
                    } else {
                        return ResponseDTO.createBySuccess();
                    }
                } else {
                    return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByErrorMessage(ResponseEnums.REFUNDS_ERROR.getMsg());
        }
        return ResponseDTO.createByError();
    }

    ////////////////////////////////////////////////////////////↓餐饮↓ //////////////////////////////////////////////////////////

    @ApiOperation(value = "餐饮订单列表", notes = "餐饮订单列表")
    @GetMapping(value = "food", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<Page<HotelBackFoodOrderVo>> foodOrderR(HotelOrderParameter.FoodOrderQuery param,
                                                              HttpServletRequest request) {
        Integer busid = getLoginUser(request).getId();
        if(param.getKeyword() != null) {
        	param.setKeyword("%" + param.getKeyword() + "%");
        	param.setPage(1);
        }
        Page<HotelBackFoodOrderVo> page = tOrderService.queryFoodOrder(busid, param);
        return ResponseDTO.createBySuccess(page);
    }

    ////////////////////////////////////////////////////////////↓导出↓ //////////////////////////////////////////////////////////

	@ApiOperation(value = "房间订单导出", notes = "房间订单导出")
    @GetMapping(value = "roomExport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void roomOrderExport(HotelOrderParameter.RoomOrderQuery param,
			HttpServletRequest request, HttpServletResponse response) {
		if(midflag != 0 && (System.currentTimeMillis() - midflag < 1000)) {
    		throw new ResponseEntityException("操作太快");
    	}
		midflag = System.currentTimeMillis();
        OutputStream outputStream = null;
        HSSFWorkbook wb = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	if(StringUtils.isEmpty(param.getRoomOutTime())) {
        		param.setRoomOutTime(format.format(System.currentTimeMillis()));
        	}
        	if(StringUtils.isEmpty(param.getRoomInTime())) {
        		Calendar cal = Calendar.getInstance();
        		cal.setTimeInMillis(System.currentTimeMillis());
        		cal.add(Calendar.MONTH, -3);
        		param.setRoomInTime(format.format(cal.getTime()));
        	}
        	Calendar inDate = Calendar.getInstance();
        	inDate.setTime(format.parse(param.getRoomInTime()));
        	Calendar outDate = Calendar.getInstance();
        	outDate.setTime(format.parse(param.getRoomOutTime()));
        	int monthDiff = (outDate.get(Calendar.YEAR) - inDate.get(Calendar.YEAR)) * 12 + outDate.get(Calendar.MONTH) - inDate.get(Calendar.MONTH);
        	if(monthDiff > 3) {
        		throw new ResponseEntityException("最大时间不可超过三个月");
        	}else {
        		Integer busid = getLoginUser(request).getId();
        		List<HotelBackRoomOrderVo> page = tOrderService.queryRoomOrderExport(busid, param);
        		String[] titles = new String[]{"订单号", "酒店名称", "姓名", "手机号", "入住时间", "离店时间", "房间类型", "预订间数",
        				"门市价", "订单状态", "支付状态", "支付方式", "住客类型", "入住标准", "证件类型", "证件号码", "性别", "消费金额",
        				"优惠金额", "应收金额", "退还金额", "实收金额"};
        		String[] contentName = new String[]{"orderNum", "hotelName", "customerName", "customerPhone", "roomInTime", "roomOutTime",
        				"categoryName", "roomOrderNum", "rackRate", "orderStatus", "payStatus", "payType", "guestType", "checkStandard", "customerIdType",
        				"customerIdCard", "customerGender", "billPrice", "discountedPrice", "receivablePrice", "refundAmount", "realPrice"};
        		wb = tOrderService.exportRoomOrder(page, contentName, titles);
        		response.setHeader("Content-Disposition", "attachment;filename=\"" +
        				URLEncoder.encode("房间订单" + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()) +
        						".xls", "UTF-8") + "\"");
        		response.setContentType("application/vnd.ms-excel");
        		outputStream = new BufferedOutputStream(response.getOutputStream());
        		wb.write(outputStream);
        	}
            outputStream.flush();
            outputStream.close();
            wb.close();
        } catch (Exception e) {
            log.error("/back/order/foodExport error");
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                log.error("/back/order/foodExport error");
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "餐饮订单导出", notes = "餐饮订单导出")
    @GetMapping(value = "foodExport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void foodOrderExport(HotelOrderParameter.FoodOrderQuery param,
    		HttpServletRequest request, HttpServletResponse response) {
    	if(midflag != 0 && (System.currentTimeMillis() - midflag < 1000)) {
    		throw new ResponseEntityException("操作太快");
    	}
		midflag = System.currentTimeMillis();
        OutputStream outputStream = null;
        HSSFWorkbook wb = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	if(StringUtils.isEmpty(param.getEndTime())) {
        		param.setEndTime(format.format(System.currentTimeMillis()));
        	}
        	if(StringUtils.isEmpty(param.getBeginTime())) {
        		Calendar cal = Calendar.getInstance();
        		cal.setTimeInMillis(System.currentTimeMillis());
        		cal.add(Calendar.MONTH, -3);
        		param.setBeginTime(format.format(cal.getTime()));
        	}
        	Calendar inDate = Calendar.getInstance();
        	inDate.setTime(format.parse(param.getEndTime()));
        	Calendar outDate = Calendar.getInstance();
        	outDate.setTime(format.parse(param.getBeginTime()));
        	int monthDiff = (outDate.get(Calendar.YEAR) - inDate.get(Calendar.YEAR)) * 12 + outDate.get(Calendar.MONTH) - inDate.get(Calendar.MONTH);
        	if(monthDiff > 3) {
        		throw new ResponseEntityException("最大时间不可超过三个月");
        	}else {
        		Integer busid = getLoginUser(request).getId();
        		List<HotelBackFoodOrderVo> page = tOrderService.queryFoodOrderExport(busid, param);
        		String[] titles = new String[]{"订单编号", "酒店名称", "预订人", "电话", "房号", "订单总额(元)", "下单时间", "菜品提供方", "订单状态", "支付状态", "支付方式"};
        		String[] contentName = new String[]{"orderNum", "hotelName", "customerName", "customerPhone", "roomNum",
        				"realPrice", "createTime", "foodProvidesName", "orderStatus", "payStatus", "payType"};
        		wb = tOrderService.exportFoodOrder(page, contentName, titles);
        		response.setHeader("Content-Disposition", "attachment;filename=\"" +
        				URLEncoder.encode("餐饮订单" + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()) +
        						".xls", "UTF-8") + "\"");
        		response.setContentType("application/vnd.ms-excel");
        		outputStream = new BufferedOutputStream(response.getOutputStream());
        		wb.write(outputStream);
        	}
            outputStream.flush();
            outputStream.close();
            wb.close();
        } catch (Exception e) {
            log.error("/back/order/foodExport error");
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (wb != null) {
                    wb.close();
                }
            } catch (IOException e) {
                log.error("/back/order/foodExport error");
                e.printStackTrace();
            }
        }
    }
    ////////////////////////////////////////////////////////////↓价格计算↓ //////////////////////////////////////////////////////////
    
    @ApiOperation(value = "价格计算", notes = "价格计算")
    @PostMapping(value = "{hotelId}/getPrice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseDTO<RoomOrderPriceVO> moblieHotelRoomGetPrice(
    		@PathVariable("hotelId") Integer hotelId,
    		@RequestBody RoomMobileParameter.BookParam bookParam,
    		HttpServletRequest request) {
		RoomOrderPriceVO price = null;
		try {
			price = orderRoomService.mobilePriceCalculation(hotelId, null, bookParam);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDTO.createByErrorMessage("价格计算出错");
		}
        return ResponseDTO.createBySuccess(price);
    }

}
