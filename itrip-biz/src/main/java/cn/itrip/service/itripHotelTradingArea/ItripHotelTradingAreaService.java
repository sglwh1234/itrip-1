package cn.itrip.service.itripHotelTradingArea;
import cn.itrip.beans.pojo.ItripHotelTradingArea;
import java.util.List;
import java.util.Map;

import cn.itrip.common.Page;
/**
*
*/
public interface ItripHotelTradingAreaService {

    public ItripHotelTradingArea getItripHotelTradingAreaById(Long id)throws Exception;

    public List<ItripHotelTradingArea>	getItripHotelTradingAreaListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelTradingAreaCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

    public Integer itriptxModifyItripHotelTradingArea(ItripHotelTradingArea itripHotelTradingArea)throws Exception;

    public Integer itriptxDeleteItripHotelTradingAreaById(Long id)throws Exception;

    public Page<ItripHotelTradingArea> queryItripHotelTradingAreaPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
