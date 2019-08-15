package cn.itrip.service.itripHotelOrder;

import cn.itrip.beans.pojo.ItripHotelOrder;
import cn.itrip.beans.pojo.ItripOrderLinkUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.common.BigDecimalUtil;
import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.mapper.itripHotelOrder.ItripHotelOrderMapper;
import cn.itrip.mapper.itripHotelRoom.ItripHotelRoomMapper;
import cn.itrip.mapper.itripOrderLinkUser.ItripOrderLinkUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItripHotelOrderServiceImpl implements ItripHotelOrderService {

    @Resource
    private ItripHotelOrderMapper itripHotelOrderMapper;
    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;
    @Resource
    private ItripOrderLinkUserMapper itripOrderLinkUserMapper;

    public ItripHotelOrder getItripHotelOrderById(Long id) throws Exception {
        return itripHotelOrderMapper.getItripHotelOrderById(id);
    }

    public List<ItripHotelOrder> getItripHotelOrderListByMap(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.getItripHotelOrderListByMap(param);
    }

    public Integer getItripHotelOrderCountByMap(Map<String, Object> param) throws Exception {
        return itripHotelOrderMapper.getItripHotelOrderCountByMap(param);
    }

    public Integer itriptxAddItripHotelOrder(ItripHotelOrder itripHotelOrder) throws Exception {
        itripHotelOrder.setCreationDate(new Date());
        return itripHotelOrderMapper.insertItripHotelOrder(itripHotelOrder);
    }

    public Integer itriptxModifyItripHotelOrder(ItripHotelOrder itripHotelOrder) throws Exception {
        itripHotelOrder.setModifyDate(new Date());
        return itripHotelOrderMapper.updateItripHotelOrder(itripHotelOrder);
    }

    public Integer itriptxDeleteItripHotelOrderById(Long id) throws Exception {
        return itripHotelOrderMapper.deleteItripHotelOrderById(id);
    }

    public Page<ItripHotelOrder> queryItripHotelOrderPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
        Integer total = itripHotelOrderMapper.getItripHotelOrderCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelOrder> itripHotelOrderList = itripHotelOrderMapper.getItripHotelOrderListByMap(param);
        page.setRows(itripHotelOrderList);
        return page;
    }

    @Override
    public BigDecimal getPayAmount(int bookingDays, Integer count, Long roomId) throws Exception {
        Double roomPrice = itripHotelRoomMapper.getItripHotelRoomById(roomId).getRoomPrice();
        return BigDecimalUtil.OperationASMD(roomPrice, bookingDays * count, BigDecimalUtil.BigDecimalOprations.multiply, 2, BigDecimal.ROUND_DOWN);
    }

    @Override
    public Long itriptxAddItripHotelOrder(ItripHotelOrder hotelOrder, List<ItripUserLinkUser> linkUsers) throws Exception {
        Long orderId = hotelOrder.getId();
        if (orderId == null || orderId == 0) {
            //添加订单信息
            hotelOrder.setCreationDate(new Date());
            //插入订单
            Integer id = itripHotelOrderMapper.insertItripHotelOrder(hotelOrder);
            //返回订单id
            orderId = hotelOrder.getId();
            System.out.println("orderId==========="+orderId);

        } else {
            //TODO 刚生成订单（消耗库存），带着订单id返回修改，再来带着订单id请求（再次验证库存）有可能造成库存不足
            //修改订单信息
            itripHotelOrderMapper.updateItripHotelOrder(hotelOrder);
            //删除订单联系人
            itripOrderLinkUserMapper.deleteItripOrderLinkUserByOrderId(orderId);
        }
        //添加订单联系人信息
        if (linkUsers != null) {
            ItripOrderLinkUser orderLinkUser = null;
            for (ItripUserLinkUser linkUser : linkUsers) {
                orderLinkUser = new ItripOrderLinkUser();
                orderLinkUser.setLinkUserId(linkUser.getId());
                orderLinkUser.setLinkUserName(linkUser.getLinkUserName());
                orderLinkUser.setOrderId(orderId);
                orderLinkUser.setCreationDate(new Date());
                orderLinkUser.setCreatedBy(hotelOrder.getUserId());
                itripOrderLinkUserMapper.insertItripOrderLinkUser(orderLinkUser);
            }
        }
        return orderId;
    }

}
