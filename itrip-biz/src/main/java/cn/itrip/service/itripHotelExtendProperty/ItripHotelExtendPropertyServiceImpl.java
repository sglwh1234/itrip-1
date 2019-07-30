package cn.itrip.service.itripHotelExtendProperty;
import cn.itrip.mapper.itripHotelExtendProperty.ItripHotelExtendPropertyMapper;
import cn.itrip.beans.pojo.ItripHotelExtendProperty;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Constants;
@Service
public class ItripHotelExtendPropertyServiceImpl implements ItripHotelExtendPropertyService {

    @Resource
    private ItripHotelExtendPropertyMapper itripHotelExtendPropertyMapper;

    public ItripHotelExtendProperty getItripHotelExtendPropertyById(Long id)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyById(id);
    }

    public List<ItripHotelExtendProperty>	getItripHotelExtendPropertyListByMap(Map<String,Object> param)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyListByMap(param);
    }

    public Integer getItripHotelExtendPropertyCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelExtendPropertyMapper.getItripHotelExtendPropertyCountByMap(param);
    }

    public Integer itriptxAddItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception{
            itripHotelExtendProperty.setCreationDate(new Date());
            return itripHotelExtendPropertyMapper.insertItripHotelExtendProperty(itripHotelExtendProperty);
    }

    public Integer itriptxModifyItripHotelExtendProperty(ItripHotelExtendProperty itripHotelExtendProperty)throws Exception{
        itripHotelExtendProperty.setModifyDate(new Date());
        return itripHotelExtendPropertyMapper.updateItripHotelExtendProperty(itripHotelExtendProperty);
    }

    public Integer itriptxDeleteItripHotelExtendPropertyById(Long id)throws Exception{
        return itripHotelExtendPropertyMapper.deleteItripHotelExtendPropertyById(id);
    }

    public Page<ItripHotelExtendProperty> queryItripHotelExtendPropertyPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelExtendPropertyMapper.getItripHotelExtendPropertyCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelExtendProperty> itripHotelExtendPropertyList = itripHotelExtendPropertyMapper.getItripHotelExtendPropertyListByMap(param);
        page.setRows(itripHotelExtendPropertyList);
        return page;
    }

}
