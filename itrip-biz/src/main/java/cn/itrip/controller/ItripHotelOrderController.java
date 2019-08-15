package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.*;
import cn.itrip.beans.vo.order.ItripAddHotelOrderVO;
import cn.itrip.beans.vo.order.RoomStoreVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import cn.itrip.common.*;
import cn.itrip.service.itripHotel.ItripHotelService;
import cn.itrip.service.itripHotelOrder.ItripHotelOrderService;
import cn.itrip.service.itripHotelRoom.ItripHotelRoomService;
import cn.itrip.service.itripHotelTempStore.ItripHotelTempStoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * description:
 * Created by Ray on 2019-08-13
 */
@Controller
@RequestMapping(value = "/api/hotelorder")
public class ItripHotelOrderController {
    @Resource
    private ValidationToken validationToken;
    @Resource
    private ItripHotelService itripHotelService;
    @Resource
    private ItripHotelRoomService itripHotelRoomService;
    @Resource
    private ItripHotelTempStoreService itripHotelTempStoreService;
    @Resource
    private SystemConfig systemConfig;
    @Resource
    private ItripHotelOrderService itripHotelOrderService;
    @RequestMapping(value = "/getpreorderinfo",method = RequestMethod.POST)
    public@ResponseBody
    Dto<RoomStoreVO> getPreorderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO, HttpServletRequest request){

        RoomStoreVO roomStoreVO=new RoomStoreVO();
        //验证登录
        String token = request.getHeader("token");
        String agent = request.getHeader("user-agent");
        try {
            validationToken.validate(agent,token);
        } catch (Exception e) {
//            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),"100000");
        }
        //验证酒店id
        Long hotelId = validateRoomStoreVO.getHotelId();
        if (hotelId == null||hotelId==0) {
            return DtoUtil.returnFail("hotelId不能为空","100510");
        }
        //验证房型id
        Long roomId = validateRoomStoreVO.getRoomId();
        if (roomId == null||roomId==0) {
            return DtoUtil.returnFail("roomId不能为空","100511");
        }
        //验证入离时间

        Date checkInDate = validateRoomStoreVO.getCheckInDate();
        Date checkOutDate = validateRoomStoreVO.getCheckOutDate();


        //封装返回的对象数据
        roomStoreVO.setCheckInDate(checkInDate);
        roomStoreVO.setCheckOutDate(checkOutDate);
        roomStoreVO.setHotelId(hotelId);
        roomStoreVO.setRoomId(roomId);
        roomStoreVO.setCount(1);
        ItripHotel hotel = null;
        try {
            hotel = itripHotelService.getItripHotelById(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return  DtoUtil.returnFail("根据酒店id获取酒店信息异常","10210");
        }
        roomStoreVO.setHotelName(hotel.getHotelName());
        ItripHotelRoom room = null;
        try {
            room = itripHotelRoomService.getItripHotelRoomById(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("根据房型id获取房型信息异常","100306");
        }
        roomStoreVO.setPrice(BigDecimal.valueOf(room.getRoomPrice()));
        //验证库存、把库存结果返回
        Map<String,Object> map=new HashMap<>();
        map.put("hotelId",hotelId);
        map.put("roomId",roomId);
        map.put("checkInDate",checkInDate);
        map.put("checkOutDate",checkOutDate);
//        map.put("count",);
        List<ItripHotelTempStore> tempStoreList=null;
        try {
            tempStoreList = itripHotelTempStoreService.getItripHotelTempStoresByMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("查询库存异常","100037");
        }
        if (tempStoreList != null) {
            //因为sql里面是根据库存升序排序的，所以取第一个的库存为最小库存
            roomStoreVO.setStore(tempStoreList.get(0).getStore());
        }
        return DtoUtil.returnDataSuccess(roomStoreVO);
    }

    /**
     * 库存验证
     * @param validateRoomStoreVO
     * @param request
     * @return
     */
    @RequestMapping(value = "/validateroomstore",method = RequestMethod.POST)
    public@ResponseBody Dto validateRoomStore(@RequestBody ValidateRoomStoreVO validateRoomStoreVO,HttpServletRequest request){
        //验证登录
        String token = request.getHeader("token");
        String agent = request.getHeader("user-agent");
        try {
            validationToken.validate(agent,token);
        } catch (Exception e) {
//            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),"100000");
        }
        //验证酒店id
        Long hotelId = validateRoomStoreVO.getHotelId();
        if (hotelId == null||hotelId==0) {
            return DtoUtil.returnFail("hotelId不能为空","100515");
        }
        //验证房型id
        Long roomId = validateRoomStoreVO.getRoomId();
        if (roomId == null||roomId==0) {
            return DtoUtil.returnFail("roomId不能为空","100516");
        }
        //验证入离时间

        Date checkInDate = validateRoomStoreVO.getCheckInDate();
        Date checkOutDate = validateRoomStoreVO.getCheckOutDate();
        if (checkInDate.getTime()>checkOutDate.getTime()) {
            return DtoUtil.returnFail("退房时间不能早于入住时间","100518");
        }
        //验证库存
        Map<String,Object> map=new HashMap<>();
        map.put("hotelId",hotelId);
        map.put("roomId",roomId);
        map.put("checkInDate",checkInDate);
        map.put("checkOutDate",checkOutDate);
        map.put("count",validateRoomStoreVO.getCount());

        try{
            Boolean haveStore= itripHotelTempStoreService.validateRoomStore(map);
            Map<String, Boolean> roomStore = new HashMap<>();
            roomStore.put("storeFlag",haveStore);
            return  DtoUtil.returnDataSuccess(roomStore);
        }catch (Exception e){
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，验证库存失败","100517");
        }
    }
    @RequestMapping(value = "/addhotelorder",method = RequestMethod.POST)
    public @ResponseBody Dto addHotelOrder(@RequestBody ItripAddHotelOrderVO itripAddHotelOrderVO,HttpServletRequest request){
        //验证登录
        String token = request.getHeader("token");
        String agent = request.getHeader("user-agent");
        try {
            validationToken.validate(agent,token);
        } catch (Exception e) {
//            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),"100000");
        }
        //表单其他验证
        Long hotelId = itripAddHotelOrderVO.getHotelId();
        Long roomId = itripAddHotelOrderVO.getRoomId();
        Date checkInDate = itripAddHotelOrderVO.getCheckInDate();
        Date checkOutDate = itripAddHotelOrderVO.getCheckOutDate();
        Integer count = itripAddHotelOrderVO.getCount();
        if (hotelId == null||roomId==null||checkInDate==null||checkOutDate==null||count==null) {
            return DtoUtil.returnFail("表单不能提交空，请填写订单信息","100506");
        }
        //库存验证
        Map<String,Object> map=new HashMap<>();
        map.put("hotelId",hotelId);
        map.put("roomId",roomId);
        map.put("checkInDate",checkInDate);
        map.put("checkOutDate",checkOutDate);
        map.put("count",count);
        try {
            if (!itripHotelTempStoreService.validateRoomStore(map)) {
                return DtoUtil.returnFail("库存不足","100507");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //封装订单pojo
        ItripHotelOrder hotelOrder=new ItripHotelOrder();
        BeanUtils.copyProperties(itripAddHotelOrderVO,hotelOrder);
        //设置用户id
        Long userId = validationToken.getCurrentUser(token).getId();
        hotelOrder.setUserId(userId);
        hotelOrder.setCreatedBy(userId);
        //订单类型:酒店产品
        hotelOrder.setOrderType(1);
        //设置订单编号：机器码+时间戳（"yyyyMMddHHmmss"）+MD（roomId+毫秒值+6随机数字）6位
        String machineCode = systemConfig.getMachineCode();
        StringBuilder orderNo=new StringBuilder();
        orderNo.append(systemConfig.getMachineCode());
        try {
            orderNo.append(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orderNo.append(MD5.getMd5(""+roomId+System.currentTimeMillis()+(Math.random()*900000+100000),6));
        hotelOrder.setOrderNo(orderNo.toString());
        //预订天数
        int bookingDays = DateUtil.getBetweenDates(checkInDate, checkOutDate).size() - 1;
        hotelOrder.setBookingDays(bookingDays);
        //订单状态:待支付
        hotelOrder.setOrderStatus(0);
        //订单金额：=房间单价*天数*房间数
        BigDecimal payAmount=null;
        try {
            payAmount = itripHotelOrderService.getPayAmount(bookingDays, count, roomId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hotelOrder.setPayAmount(payAmount.doubleValue());
        //设置入住人姓名
        List<ItripUserLinkUser> linkUsers = itripAddHotelOrderVO.getLinkUser();
        int size = linkUsers.size();
        StringBuilder userNames = new StringBuilder();
        //王琳琳,lisa,涛涛
        if(linkUsers!=null&& size >0){
            for (int i = 0; i < size; i++) {
                userNames.append(linkUsers.get(i).getLinkUserName());
                if(i<size-1){
                    userNames.append(",");
                }
            }
        }
        hotelOrder.setLinkUserName(userNames.toString());
        //设置客户端
        if(token.startsWith("token:PC")){
            hotelOrder.setBookType(0);
        }else if(token.startsWith("token:MOBILE")){
            hotelOrder.setBookType(1);
        }else{
            hotelOrder.setBookType(2);
        }
        //执行添加订单操作
        Long orderId=null;
        try {
            Map<String, Object> outMap = new HashMap<>();
            orderId = itripHotelOrderService.itriptxAddItripHotelOrder(hotelOrder,linkUsers);
            outMap.put("orderId",orderId);
            outMap.put("orderNo",orderNo);
            return DtoUtil.returnDataSuccess(outMap);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常,生成订单失败","100505");
        }
    }
}
