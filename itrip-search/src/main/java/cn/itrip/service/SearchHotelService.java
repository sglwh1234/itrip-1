package cn.itrip.service;

import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.Page;

import java.util.List;

public interface SearchHotelService {
    /**
     * 分页查询酒店列表
     * @param searchHotelVO
     * @return
     */
    Page<ItripHotelVO> searchHotelListPage(SearchHotelVO searchHotelVO) throws  Exception;
    //根据热门城市获取酒店列表
    List<ItripHotelVO> searchHotHotelList(String destination) throws  Exception;
}
