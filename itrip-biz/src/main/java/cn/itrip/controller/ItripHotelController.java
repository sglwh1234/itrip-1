package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripImage;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.ItripImageVO;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.service.itripAreaDic.ItripAreaDicService;
import cn.itrip.service.itripHotel.ItripHotelService;
import cn.itrip.service.itripImage.ItripImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/hotel")
public class ItripHotelController {
    @Resource
    private ItripAreaDicService itripAreaDicService;
    @Resource
    private ItripImageService itripImageService;
    @Resource
    private ItripHotelService itripHotelService;
    /**
     * 查询热门城市列表
     * @param type
     * @return
     */
    @RequestMapping(value = "/queryhotcity/{type}",method = RequestMethod.GET)
    public@ResponseBody
    Dto queryHotCity(@PathVariable Integer type) {
        if (type == null || type == 0) {
            return DtoUtil.returnFail("国家type不能为空", "10201 ");
        }
        //封装查询参数
        Map<String, Object> map = new HashMap();
        map.put("isHot", 1);
        map.put("isChina", type);
        try {
            //调用业务逻辑，从数据库查询热门城市列表（pojo）
            List<ItripAreaDic> list = itripAreaDicService.getItripAreaDicListByMap(map);
            //把pojo列表转换成vo列表
            List<ItripAreaDicVO> areaDicVOS = new ArrayList<>();
            ItripAreaDicVO areaDicVO = null;
            for (ItripAreaDic dic : list) {
               areaDicVO= new ItripAreaDicVO();
                BeanUtils.copyProperties(dic, areaDicVO);
                areaDicVOS.add(areaDicVO);
            }

            return DtoUtil.returnDataSuccess(areaDicVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取热门城市失败", "10202");
        }
    }

    /**
     * 根据城市查询商圈列表
     * @param cityId
     * @return
     */
    @RequestMapping(value = "querytradearea/{cityId}",method = RequestMethod.GET)
    public@ResponseBody Dto queryTradeArea(@PathVariable Integer cityId){
        if (cityId == null||cityId==0) {
            return DtoUtil.returnFail("cityId不能为空","10203 ");
        }
        //封装查询参数
        Map<String, Object> map = new HashMap();
        map.put("isTradingArea", 1);
        map.put("parent", cityId);
        try {
            //调用业务逻辑，从数据库查询商圈列表（pojo）
            List<ItripAreaDic> list = itripAreaDicService.getItripAreaDicListByMap(map);
            //把pojo列表转换成vo列表
            List<ItripAreaDicVO> areaDicVOS = new ArrayList<>();
            ItripAreaDicVO areaDicVO = null;
            for (ItripAreaDic dic : list) {
                areaDicVO= new ItripAreaDicVO();
                BeanUtils.copyProperties(dic, areaDicVO);
                areaDicVOS.add(areaDicVO);
            }
            return DtoUtil.returnDataSuccess(areaDicVOS);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取城市商圈失败", "10204");
        }

    }

    /**
     * 根据酒店id查询酒店图片
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/getimg/{targetId}",method = RequestMethod.GET)
    public@ResponseBody Dto getImg(@PathVariable String targetId){
        if (EmptyUtils.isEmpty(targetId)) {
            return DtoUtil.returnFail("酒店targetid不能为空","100213 ");
        }
        Map<String, Object> map = new HashMap();
        map.put("type",0);//酒店图片
        map.put("targetId", Long.parseLong(targetId));//目标酒店id

        try {

            List<ItripImage> list = itripImageService.getItripImageListByMap(map);
            List<ItripImageVO> imageVOList=new ArrayList<>();
            ItripImageVO imageVO=null;
            for (ItripImage image : list) {
                imageVO=new ItripImageVO();
                BeanUtils.copyProperties(image,imageVO);
                imageVOList.add(imageVO);
            }
            return  DtoUtil.returnDataSuccess(imageVOList);

        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取酒店图片失败","100212 ");
        }

    }
    @RequestMapping(value = "/getvideodesc/{hotelId}",method = RequestMethod.GET)
    public@ResponseBody Dto getVideoDesc(@PathVariable("hotelId") String hotelID){
        if (EmptyUtils.isEmpty(hotelID)) {
            return DtoUtil.returnFail("酒店id不能为空","100215");
        }

        try {
            HotelVideoDescVO videoDescVO =itripHotelService.getItripHotelVideoDesc(Long.parseLong(hotelID));
            return  DtoUtil.returnDataSuccess(videoDescVO);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常，获取视频信息失败","100214");
        }


    }
}
