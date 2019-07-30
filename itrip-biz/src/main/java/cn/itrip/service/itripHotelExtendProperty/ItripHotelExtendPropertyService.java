package cn.itrip.service.itripHotelExtendProperty;
import cn.itrip.beans.pojo.ItripHotelExtendProperty;
import java.util.List;
import java.util.Map;

import cn.itrip.common.Page;
/**
*
*/
public interface ItripHotelExtendPropertyService {

    public ItripHotelExtendProperty getItripHotelExtendPropertyById(Long id)throws Exception;

    public List<ItripHotelExtendProperty>	getItripHotelExtendPropertyListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelExtendPropertyCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

    public Integer itriptxModifyItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception;

    public Integer itriptxDeleteItripHotelExtendPropertyById(Long id)throws Exception;

    public Page<ItripHotelExtendProperty> queryItripHotelExtendPropertyPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
