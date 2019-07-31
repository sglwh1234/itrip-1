package cn.itrip.service.itripUser;

import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.common.Page;

import java.util.List;
import java.util.Map;

/**
*
*/
public interface ItripUserService {

    public ItripUser getItripUserById(Long id)throws Exception;

    public List<ItripUser>	getItripUserListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripUserCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripUser(ItripUser itripUser)throws Exception;

    public Integer itriptxModifyItripUser(ItripUser itripUser)throws Exception;

    public Integer itriptxDeleteItripUserById(Long id)throws Exception;

    public Page<ItripUser> queryItripUserPageByMap(Map<String,Object> param, Integer pageNo, Integer pageSize)throws Exception;
}
