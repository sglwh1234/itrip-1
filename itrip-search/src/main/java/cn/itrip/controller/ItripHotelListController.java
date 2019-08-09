package cn.itrip.controller;

import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.service.SearchHotelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/api/hotellist")
public class ItripHotelListController {
    @Resource(name="searchHotelService")
    private SearchHotelService searchHotelService;

    /**
     * 根据搜索条件检索城市列表
     * @param searchHotelVO
     * @return
     */
    @RequestMapping(value = "/searchItripHotelPage",method = RequestMethod.POST)
    public@ResponseBody Dto SearchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO){
        if (EmptyUtils.isEmpty(searchHotelVO)||EmptyUtils.isEmpty(searchHotelVO.getDestination())) {
            return DtoUtil.returnFail("目的地不能为空","20002");
        }

        Page page= null;
        try {
            page = searchHotelService.searchHotelListPage(searchHotelVO);
        } catch (Exception e) {
//            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(),"20001");
        }

        return DtoUtil.returnDataSuccess(page);
    }

    /**
     * 根据热门城市检索酒店列表
     * @param searchHotelVO
     * @return
     */
    @RequestMapping(value = "/searchItripHotelListByHotCity",method = RequestMethod.POST)
    public@ResponseBody Dto SearchItripHotelListByHotCity(@RequestBody SearchHotelVO searchHotelVO){
        if (EmptyUtils.isEmpty(searchHotelVO)||EmptyUtils.isEmpty(searchHotelVO.getDestination())) {
            return DtoUtil.returnFail("城市不能为空","20004");
        }

        try {
            List<ItripHotelVO> list= searchHotelService.searchHotHotelList(searchHotelVO.getDestination());
            return  DtoUtil.returnDataSuccess(list);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("系统异常,获取热门城市酒店列表失败","20003");
        }

    }
}
