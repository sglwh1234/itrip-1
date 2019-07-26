package cn.itrip.mapper.itripHotelOrder;
import cn.itrip.pojo.ItripHotelOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelOrderMapper {

	public ItripHotelOrder getItripHotelOrderById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotelOrder>	getItripHotelOrderListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripHotelOrderCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

	public Integer updateItripHotelOrder(ItripHotelOrder itripHotelOrder)throws Exception;

	public Integer deleteItripHotelOrderById(@Param(value = "id") Long id)throws Exception;

}
