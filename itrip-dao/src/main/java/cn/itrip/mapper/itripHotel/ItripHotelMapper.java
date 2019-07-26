package cn.itrip.mapper.itripHotel;
import cn.itrip.pojo.ItripHotel;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

	public ItripHotel getItripHotelById(@Param(value = "id") Long id)throws Exception;

	public List<ItripHotel>	getItripHotelListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripHotelCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer updateItripHotel(ItripHotel itripHotel)throws Exception;

	public Integer deleteItripHotelById(@Param(value = "id") Long id)throws Exception;

}
