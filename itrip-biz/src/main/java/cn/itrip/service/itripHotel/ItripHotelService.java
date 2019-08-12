package cn.itrip.service.itripHotel;
import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.beans.vo.hotel.HotelVideoDescVO;
import cn.itrip.common.Page;

import java.util.List;
import java.util.Map;
/**
*
*/
public interface ItripHotelService {

    public ItripHotel getItripHotelById(Long id)throws Exception;

    public List<ItripHotel>	getItripHotelListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotel(ItripHotel itripHotel)throws Exception;

    public Integer itriptxModifyItripHotel(ItripHotel itripHotel)throws Exception;

    public Integer itriptxDeleteItripHotelById(Long id)throws Exception;

    public Page<ItripHotel> queryItripHotelPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    /**
     * 根据酒店id获取视频信息
     * @return
     */
    HotelVideoDescVO getItripHotelVideoDesc(Long hotelId)throws  Exception;
}
