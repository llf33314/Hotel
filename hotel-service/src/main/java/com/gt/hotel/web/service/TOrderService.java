package com.gt.hotel.web.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.gt.hotel.base.BaseService;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.CheckInParam;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.OffLineOrder;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.vo.HotelBackFoodOrderVo;
import com.gt.hotel.vo.HotelBackRoomOrderVo;
import org.joda.time.DateTime;

import java.util.List;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) 服务类
 * </p>
 *
 * @author
 * @since 2017-10-27
 */
public interface TOrderService extends BaseService<TOrder> {

    /**
     * 房间订单列表
     *
     * @param busid 用户ID
     * @param param
     * @return
     */
    Page<HotelBackRoomOrderVo> queryRoomOrder(Integer busid, RoomOrderQuery param);

    /**
     * 餐饮订单列表
     *
     * @param busid 用户ID
     * @param param
     * @return
     */
    Page<HotelBackFoodOrderVo> queryFoodOrder(Integer busid, FoodOrderQuery param);

    /**
     * 添加线下订单
     *
     * @param busid
     * @param order
     */
    void AddOffLineOrder(Integer busid, OffLineOrder order);

    /**
     * 房间订单详情
     *
     * @param id
     * @return
     */
    HotelBackRoomOrderVo queryRoomOrderOne(Integer id);

    /**
     * 房间订单入住
     *
     * @param busid
     * @param orderId
     * @param param
     */
    void checkIn(Integer busid, Integer orderId, CheckInParam param);

    /**
     * 房间订单列表
     *
     * @param busid
     * @param param
     * @return
     */
    List<HotelBackRoomOrderVo> queryRoomOrderExport(Integer busid, RoomOrderQuery param);

    /**
     * 餐饮订单列表
     *
     * @param busid
     * @param param
     * @return
     */
    List<HotelBackFoodOrderVo> queryFoodOrderExport(Integer busid, FoodOrderQuery param);

    /**
     * 手机端 预订订单
     *
     * @param busId     用户ID
     * @param hid       酒店ID
     * @param cid       房型ID
     * @param startTime 入住日期
     * @param endTime   离店日期
     */
    void mobileCheckNewOrder(Integer busId, Integer hid, Integer cid, DateTime startTime, DateTime endTime);

}
