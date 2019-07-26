package cn.itrip.service.itripOrderLinkUser;
import cn.itrip.pojo.ItripOrderLinkUser;
import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Page;
/**
*
*/
public interface ItripOrderLinkUserService {

    public ItripOrderLinkUser getItripOrderLinkUserById(Long id)throws Exception;

    public List<ItripOrderLinkUser>	getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripOrderLinkUserCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

    public Integer itriptxModifyItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception;

    public Integer itriptxDeleteItripOrderLinkUserById(Long id)throws Exception;

    public Page<ItripOrderLinkUser> queryItripOrderLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
