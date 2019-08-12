package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.pojo.ItripLabelDic;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.ItripLabelDicVO;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.beans.vo.hotelroom.SearchHotelRoomVO;
import cn.itrip.common.DateUtil;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.itripHotelRoom.ItripHotelRoomService;
import cn.itrip.service.itripImage.ItripImageService;
import cn.itrip.service.itripLabelDic.ItripLabelDicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * description:
 * Created by Ray on 2019-08-12
 */
@Controller
@RequestMapping("/api/hotelroom")
public class ItripHotelRoomController {
    @Resource
    private ItripImageService itripImageService;
    @Resource
    private ItripLabelDicService itripLabelDicService;
    @Resource
    private ItripHotelRoomService itripHotelRoomService;
    /**
     * 根据房型id获取图片
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/getimg/{targetId}",method = RequestMethod.GET)
    public @ResponseBody Dto getHotelRoomImg(@PathVariable String targetId){
        if (EmptyUtils.isEmpty(targetId)) {
            return DtoUtil.returnFail("酒店房型id不能为空","100302");
        }

        Map<String, Object> map=new HashMap<>();
        map.put("targetId",Long.parseLong(targetId));
        try {
            List<ItripImage> list = itripImageService.getItripImageListByMap(map);
            List<ItripImageVO> imageVOList=new ArrayList<>();
            ItripImageVO imageVO=null;
            for (ItripImage image : list) {
                imageVO=new ItripImageVO();
                BeanUtils.copyProperties(image,imageVO);
                imageVOList.add(imageVO);
            }
            return DtoUtil.returnDataSuccess(imageVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取酒店房型图片失败", "100301 ");
        }

    }

    /**
     * 查询床型列表
     * @return
     */
    @RequestMapping(value = "/queryhotelroombed",method = RequestMethod.GET)
    public@ResponseBody Dto getHotelRoomBed(){
        Map<String, Object> map=new HashMap<>();
        map.put("parentId",1);
        try {
            List<ItripLabelDic> list = itripLabelDicService.getItripLabelDicListByMap(map);
            List<ItripLabelDicVO> bedList=new ArrayList<>();
            ItripLabelDicVO bedVo=null;
            for (ItripLabelDic labelDic : list) {
                bedVo=new ItripLabelDicVO();
                BeanUtils.copyProperties(labelDic,bedVo);
                bedList.add(bedVo);
            }
            return DtoUtil.returnDataSuccess(bedList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("获取酒店房间床型失败", "100305");
        }
    }

    /**
     * 搜索酒店房型列表
     * @param searchHotelRoomVO
     * @return
     */
    @RequestMapping(value = "/queryhotelroombyhotel",method = RequestMethod.POST)
    public@ResponseBody Dto queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO){
        Long hotelId = searchHotelRoomVO.getHotelId();
        if (hotelId == null||hotelId==0) {
            return DtoUtil.returnFail("酒店id不能为空","100303");
        }
        Date startDate = searchHotelRoomVO.getStartDate();
        Date endDate = searchHotelRoomVO.getEndDate();
        if (EmptyUtils.isEmpty(startDate)||EmptyUtils.isEmpty(endDate)) {
            return DtoUtil.returnFail("入住或退房时间不能为空","100303");
        }
        if(startDate.getTime()>endDate.getTime()){
            return DtoUtil.returnFail("入住时间不能晚于退房时间","100303");
        }
        //查询房型列表
        Map<String, Object> map=new HashMap<>();
        map.put("hotelId",hotelId);
        map.put("roomBedTypeId",searchHotelRoomVO.getRoomBedTypeId());
        map.put("isHavingBreakfast",searchHotelRoomVO.getIsHavingBreakfast());
        map.put("isBook",searchHotelRoomVO.getIsBook());
        map.put("isCancel",searchHotelRoomVO.getIsCancel());
        map.put("isTimelyResponse",searchHotelRoomVO.getIsTimelyResponse());
        map.put("payType",searchHotelRoomVO.getPayType());
        List<Date> dateList = DateUtil.getBetweenDates(startDate, endDate);
        for (Date date : dateList) {
            System.out.println(date.toLocaleString()+"-------");
        }
        map.put("dateList", dateList);

        try {
            List<ItripHotelRoom> roomList = itripHotelRoomService.getItripHotelRoomListByMap(map);
            List<ItripHotelRoomVO> roomVOList=new ArrayList<>();
            ItripHotelRoomVO roomVO=null;
            for (ItripHotelRoom room : roomList) {
                roomVO=new ItripHotelRoomVO();
                BeanUtils.copyProperties(room,roomVO);
                roomVOList.add(roomVO);
            }
            return DtoUtil.returnDataSuccess(roomVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取房型列表失败","100304 ");
        }
    }
}
