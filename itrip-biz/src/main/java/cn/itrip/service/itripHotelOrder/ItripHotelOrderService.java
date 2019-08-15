package cn.itrip.service.itripHotelOrder;
import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.common.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
/**
*
*/
public interface ItripHotelOrderService {

    public ItripHotelOrder getItripHotelOrderById(Long id)throws Exception;

    public List<ItripHotelOrder>	getItripHotelOrderListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelOrderCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

    public Integer itriptxModifyItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

    public Integer itriptxDeleteItripHotelOrderById(Long id)throws Exception;

    public Page<ItripHotelOrder> queryItripHotelOrderPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    /**
     * 计算订单金额
     * @param bookingDays
     * @param count
     * @param roomId
     */
    BigDecimal getPayAmount(int bookingDays, Integer count, Long roomId) throws Exception;

    /**
     * 添加订单及订单联系人
     * @param hotelOrder 订单对象
     * @param linkUsers 入住人列表
     * @return  订单id
     */
    Long itriptxAddItripHotelOrder(ItripHotelOrder hotelOrder, List<ItripUserLinkUser> linkUsers) throws Exception;
}
