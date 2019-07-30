package cn.itrip.service.itripHotelFeature;
import cn.itrip.beans.pojo.ItripHotelFeature;
import java.util.List;
import java.util.Map;

import cn.itrip.common.Page;
/**
*
*/
public interface ItripHotelFeatureService {

    public ItripHotelFeature getItripHotelFeatureById(Long id)throws Exception;

    public List<ItripHotelFeature>	getItripHotelFeatureListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelFeatureCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

    public Integer itriptxModifyItripHotelFeature(ItripHotelFeature itripHotelFeature)throws Exception;

    public Integer itriptxDeleteItripHotelFeatureById(Long id)throws Exception;

    public Page<ItripHotelFeature> queryItripHotelFeaturePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
