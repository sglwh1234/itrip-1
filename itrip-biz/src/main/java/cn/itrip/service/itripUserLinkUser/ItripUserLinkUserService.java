package cn.itrip.service.itripUserLinkUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import java.util.List;
import java.util.Map;

import cn.itrip.common.Page;
/**
*
*/
public interface ItripUserLinkUserService {

    public ItripUserLinkUser getItripUserLinkUserById(Long id)throws Exception;

    public List<ItripUserLinkUser>	getItripUserLinkUserListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripUserLinkUserCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    public Integer itriptxModifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;

    public Integer itriptxDeleteItripUserLinkUserById(Long id)throws Exception;

    public Page<ItripUserLinkUser> queryItripUserLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
