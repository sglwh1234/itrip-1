package cn.itrip.service.itripOrderLinkUser;
import cn.itrip.mapper.itripOrderLinkUser.ItripOrderLinkUserMapper;
import cn.itrip.beans.pojo.ItripOrderLinkUser;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Constants;
@Service
public class ItripOrderLinkUserServiceImpl implements ItripOrderLinkUserService {

    @Resource
    private ItripOrderLinkUserMapper itripOrderLinkUserMapper;

    public ItripOrderLinkUser getItripOrderLinkUserById(Long id)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserById(id);
    }

    public List<ItripOrderLinkUser>	getItripOrderLinkUserListByMap(Map<String,Object> param)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserListByMap(param);
    }

    public Integer getItripOrderLinkUserCountByMap(Map<String,Object> param)throws Exception{
        return itripOrderLinkUserMapper.getItripOrderLinkUserCountByMap(param);
    }

    public Integer itriptxAddItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception{
            itripOrderLinkUser.setCreationDate(new Date());
            return itripOrderLinkUserMapper.insertItripOrderLinkUser(itripOrderLinkUser);
    }

    public Integer itriptxModifyItripOrderLinkUser(ItripOrderLinkUser itripOrderLinkUser)throws Exception{
        itripOrderLinkUser.setModifyDate(new Date());
        return itripOrderLinkUserMapper.updateItripOrderLinkUser(itripOrderLinkUser);
    }

    public Integer itriptxDeleteItripOrderLinkUserById(Long id)throws Exception{
        return itripOrderLinkUserMapper.deleteItripOrderLinkUserById(id);
    }

    public Page<ItripOrderLinkUser> queryItripOrderLinkUserPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripOrderLinkUserMapper.getItripOrderLinkUserCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripOrderLinkUser> itripOrderLinkUserList = itripOrderLinkUserMapper.getItripOrderLinkUserListByMap(param);
        page.setRows(itripOrderLinkUserList);
        return page;
    }

}
