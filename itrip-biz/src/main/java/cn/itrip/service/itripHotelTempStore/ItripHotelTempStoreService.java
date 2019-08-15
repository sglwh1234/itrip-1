package cn.itrip.service.itripHotelTempStore;
import cn.itrip.beans.pojo.ItripHotelTempStore;
import cn.itrip.common.Page;

import java.util.List;
import java.util.Map;
/**
*
*/
public interface ItripHotelTempStoreService {

    public ItripHotelTempStore getItripHotelTempStoreById(Long id)throws Exception;

    public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

    public Integer itriptxModifyItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

    public Integer itriptxDeleteItripHotelTempStoreById(Long id)throws Exception;

    public Page<ItripHotelTempStore> queryItripHotelTempStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;

    /**
     * 查询酒店房型库存量
     * @param map
     * @return
     */
    List<ItripHotelTempStore> getItripHotelTempStoresByMap(Map<String, Object> map) throws Exception;

    /**
     * 验证房间库存
     * @param map
     * @return  有or无
     */
    Boolean validateRoomStore(Map<String, Object> map) throws Exception;
}
