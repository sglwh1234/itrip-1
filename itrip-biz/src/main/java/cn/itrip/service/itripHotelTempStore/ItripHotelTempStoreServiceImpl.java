package cn.itrip.service.itripHotelTempStore;
import cn.itrip.beans.pojo.ItripHotelTempStore;
import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.mapper.itripHotelTempStore.ItripHotelTempStoreMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ItripHotelTempStoreServiceImpl implements ItripHotelTempStoreService {

    @Resource
    private ItripHotelTempStoreMapper itripHotelTempStoreMapper;

    public ItripHotelTempStore getItripHotelTempStoreById(Long id)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreById(id);
    }

    public List<ItripHotelTempStore>	getItripHotelTempStoreListByMap(Map<String,Object> param)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreListByMap(param);
    }

    public Integer getItripHotelTempStoreCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelTempStoreMapper.getItripHotelTempStoreCountByMap(param);
    }

    public Integer itriptxAddItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception{
            itripHotelTempStore.setCreationDate(new Date());
            return itripHotelTempStoreMapper.insertItripHotelTempStore(itripHotelTempStore);
    }

    public Integer itriptxModifyItripHotelTempStore(ItripHotelTempStore itripHotelTempStore)throws Exception{
        itripHotelTempStore.setModifyDate(new Date());
        return itripHotelTempStoreMapper.updateItripHotelTempStore(itripHotelTempStore);
    }

    public Integer itriptxDeleteItripHotelTempStoreById(Long id)throws Exception{
        return itripHotelTempStoreMapper.deleteItripHotelTempStoreById(id);
    }

    public Page<ItripHotelTempStore> queryItripHotelTempStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelTempStoreMapper.getItripHotelTempStoreCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelTempStore> itripHotelTempStoreList = itripHotelTempStoreMapper.getItripHotelTempStoreListByMap(param);
        page.setRows(itripHotelTempStoreList);
        return page;
    }

    @Override
    public List<ItripHotelTempStore> getItripHotelTempStoresByMap(Map<String, Object> map) throws Exception {

        //调查存储过程，刷新实时库存表
        itripHotelTempStoreMapper.flushStore(map);
        //计算算库存数量
       return itripHotelTempStoreMapper.getItripHotelTempStroes(map);

    }

}
