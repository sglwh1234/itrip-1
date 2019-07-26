package cn.itrip.service.itripUserLinkUser;
import cn.itrip.mapper.itripUserLinkUser.ItripUserLinkUserMapper;
import cn.itrip.pojo.ItripUserLinkUser;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Constants;
@Service
public class ItripUserLinkUserServiceImpl implements ItripUserLinkUserService {

    @Resource
    private ItripUserLinkUserMapper itripUserLinkUserMapper;

    public ItripUserLinkUser getItripUserLinkUserById(Long id)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserById(id);
    }

    public List<ItripUserLinkUser>	getItripUserLinkUserListByMap(Map<String,Object> param)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserListByMap(param);
    }

    public Integer getItripUserLinkUserCountByMap(Map<String,Object> param)throws Exception{
        return itripUserLinkUserMapper.getItripUserLinkUserCountByMap(param);
    }

    public Integer itriptxAddItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
            itripUserLinkUser.setCreationDate(new Date());
            return itripUserLinkUserMapper.insertItripUserLinkUser(itripUserLinkUser);
    }

    public Integer itriptxModifyItripUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception{
        itripUserLinkUser.setModifyDate(new Date());
        return itripUserLinkUserMapper.updateItripUserLinkUser(itripUserLinkUser);
    }

    public Integer itriptxDeleteItripUserLinkUserById(Long id)throws Exception{
        return itripUserLinkUserMapper.deleteItripUserLinkUserById(id);
    }

    public Page<ItripUserLinkUser> queryItripUserLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripUserLinkUserMapper.getItripUserLinkUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripUserLinkUser> itripUserLinkUserList = itripUserLinkUserMapper.getItripUserLinkUserListByMap(param);
        page.setRows(itripUserLinkUserList);
        return page;
    }

}
