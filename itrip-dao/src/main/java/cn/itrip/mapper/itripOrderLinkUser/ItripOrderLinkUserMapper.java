package cn.itrip.mapper.itripOrderLinkUser;
import cn.itrip.beans.pojo.ItripOrderLinkUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripOrderLinkUserMapper {

	public ItripOrderLinkUser getItripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

	public List<ItripOrderLinkUser>	getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripOrderLinkUserCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

	public Integer updateItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

	public Integer deleteItripOrderLinkUserById(@Param(value = "id") Long id)throws Exception;

}
