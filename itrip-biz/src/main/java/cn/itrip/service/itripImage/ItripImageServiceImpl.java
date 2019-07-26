package cn.itrip.service.itripImage;
import cn.itrip.mapper.itripImage.ItripImageMapper;
import cn.itrip.pojo.ItripImage;
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
public class ItripImageServiceImpl implements ItripImageService {

    @Resource
    private ItripImageMapper itripImageMapper;

    public ItripImage getItripImageById(Long id)throws Exception{
        return itripImageMapper.getItripImageById(id);
    }

    public List<ItripImage>	getItripImageListByMap(Map<String,Object> param)throws Exception{
        return itripImageMapper.getItripImageListByMap(param);
    }

    public Integer getItripImageCountByMap(Map<String,Object> param)throws Exception{
        return itripImageMapper.getItripImageCountByMap(param);
    }

    public Integer itriptxAddItripImage(ItripImage itripImage)throws Exception{
            itripImage.setCreationDate(new Date());
            return itripImageMapper.insertItripImage(itripImage);
    }

    public Integer itriptxModifyItripImage(ItripImage itripImage)throws Exception{
        itripImage.setModifyDate(new Date());
        return itripImageMapper.updateItripImage(itripImage);
    }

    public Integer itriptxDeleteItripImageById(Long id)throws Exception{
        return itripImageMapper.deleteItripImageById(id);
    }

    public Page<ItripImage> queryItripImagePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripImageMapper.getItripImageCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripImage> itripImageList = itripImageMapper.getItripImageListByMap(param);
        page.setRows(itripImageList);
        return page;
    }

}
