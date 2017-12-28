package com.gt.hotel.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gt.hotel.entity.TOrder;
import com.gt.hotel.param.HotelOrderParameter.FoodOrderQuery;
import com.gt.hotel.param.HotelOrderParameter.RoomOrderQuery;
import com.gt.hotel.param.HotelPage;
import com.gt.hotel.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 算客户实际 预约(到店办理)->入住->消费(酒店内消费)->退房->结算 的所有消费(房费+其他费) Mapper 接口
 * </p>
 *
 * @author
 * @since 2017-10-27
 */
public interface TOrderDAO extends BaseMapper<TOrder> {

    /**
     * 房间订单列表
     *
     * @param busid 用户ID
     * @param param
     * @param page  分页对象
     * @return
     */
    List<HotelBackRoomOrderVo> queryRoomOrder(@Param("busid") Integer busid, @Param("param") RoomOrderQuery param, @Param("page") Pagination page);

    List<HotelBackRoomOrderVo> queryRoomOrder(@Param("busid") Integer busid, @Param("param") RoomOrderQuery param);

    /**
     * 餐饮订单列表
     *
     * @param busid 用户ID
     * @param param
     * @param page  分页对象
     * @return
     */
    List<HotelBackFoodOrderVo> queryFoodOrder(@Param("busid") Integer busid, @Param("param") FoodOrderQuery param, @Param("page") Pagination page);

    List<HotelBackFoodOrderVo> queryFoodOrder(@Param("busid") Integer busid, @Param("param") FoodOrderQuery param);

    /**
     * 房间订单对象 -> 房间列表
     *
     * @param orderId 订单ID
     * @return
     */
    List<OrderRoomCustomerVo> queryRoomOrderOneRooms(@Param("orderId") Integer orderId);

    /**
     * 移动端 押金
     *
     * @param id
     * @param page
     * @return
     */
    List<DepositVo> queryMobileDeposit(@Param("memberId") Integer memberId, @Param("page") Pagination page);

    /**
     * 已入住订单
     *
     * @param memberId
     * @return
     */
    List<com.gt.hotel.vo.HotelBackRoomOrderVo> checkInOrder(@Param("memberId") Integer memberId);

    /**
     * 酒店营业状况
     *
     * @param busId
     * @param nowStart
     * @param nowEnd
     * @return
     */
    BusinessConditionsVo erpGetBusinessConditions(@Param("busId") Integer busId, @Param("shopId") Integer shopId, @Param("nowStart") String nowStart, @Param("nowEnd") String nowEnd);

    /**
     * @param busId
     * @param shopId
     * @param startDate
     * @param endDate
     * @return
     */
    List<TOrder> getTotalRevenue(@Param("busId") Integer busId, @Param("shopId") Integer shopId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 收入明细
     *
     * @param busId
     * @param shopId
     * @param hpage
     * @param page
     * @return
     */
    List<IncomeDetailsVo> getIncomeDetailsByDate(@Param("busId") Integer busId, @Param("shopId") Integer shopId, @Param("param") HotelPage hpage, @Param("page") Pagination page);

    /**
     * 根据房型获取 今日预订(入住) 客房数量
     * <pre>
     *     categoryId is null 则获取酒店当天预订的数量。否则 获取酒店的房型的预订数量
     * </pre>
     *
     * @param hotelId    酒店ID 必填
     * @param categoryId 房型ID 非必填
     * @return int 今日入住客房数量
     */
    Integer erpFindTodayBookingByCategoryId(@Param("hotelId") Integer hotelId, @Param("categoryId") Integer categoryId);
}