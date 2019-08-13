package cn.itrip.mapper.itripHotelTempStore;
import cn.itrip.beans.pojo.ItripHotelTempStore;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelTempStoreMapper {

	public ItripHotelTempStore getItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer updateItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception;

	public Integer deleteItripHotelTempStoreById(@Param(value = "id") Long id)throws Exception;

	/**
	 * 调用存储过程，更新实时库存表
	 * @param map
	 */
    void flushStore(Map<String, Object> map)throws  Exception;

	/**
	 * 查询时间段内酒店房型库存
	 * @param map
	 * @return
	 */
	List<ItripHotelTempStore> getItripHotelTempStroes(Map<String, Object> map);
}
