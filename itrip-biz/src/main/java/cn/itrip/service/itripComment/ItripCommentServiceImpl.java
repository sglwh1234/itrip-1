package cn.itrip.service.itripComment;
import cn.itrip.mapper.itripComment.ItripCommentMapper;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Constants;
@Service
public class ItripCommentServiceImpl implements ItripCommentService {

    @Resource
    private ItripCommentMapper itripCommentMapper;

    public ItripComment getItripCommentById(Long id)throws Exception{
        return itripCommentMapper.getItripCommentById(id);
    }

    public List<ItripComment>	getItripCommentListByMap(Map<String,Object> param)throws Exception{
        return itripCommentMapper.getItripCommentListByMap(param);
    }

    public Integer getItripCommentCountByMap(Map<String,Object> param)throws Exception{
        return itripCommentMapper.getItripCommentCountByMap(param);
    }

    public Integer itriptxAddItripComment(ItripComment itripComment)throws Exception{
            itripComment.setCreationDate(new Date());
            return itripCommentMapper.insertItripComment(itripComment);
    }

    public Integer itriptxModifyItripComment(ItripComment itripComment)throws Exception{
        itripComment.setModifyDate(new Date());
        return itripCommentMapper.updateItripComment(itripComment);
    }

    public Integer itriptxDeleteItripCommentById(Long id)throws Exception{
        return itripCommentMapper.deleteItripCommentById(id);
    }

    public Page<ItripComment> queryItripCommentPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripCommentMapper.getItripCommentCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripComment> itripCommentList = itripCommentMapper.getItripCommentListByMap(param);
        page.setRows(itripCommentList);
        return page;
    }

}
