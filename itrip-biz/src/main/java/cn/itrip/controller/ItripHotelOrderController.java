package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripHotelTempStore;
import cn.itrip.beans.vo.order.RoomStoreVO;
import cn.itrip.beans.vo.order.ValidateRoomStoreVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.itripHotel.ItripHotelService;
import cn.itrip.service.itripHotelRoom.ItripHotelRoomService;
import cn.itrip.service.itripHotelTempStore.ItripHotelTempStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //验证库存

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
}
