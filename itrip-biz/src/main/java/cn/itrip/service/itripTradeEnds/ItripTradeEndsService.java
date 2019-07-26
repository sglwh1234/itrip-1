package cn.itrip.service.itripTradeEnds;
import cn.itrip.pojo.ItripTradeEnds;
import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Page;
/**
*
*/
public interface ItripTradeEndsService {

    public ItripTradeEnds getItripTradeEndsById(Long id)throws Exception;

    public List<ItripTradeEnds>	getItripTradeEndsListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripTradeEndsCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

    public Integer itriptxModifyItripTradeEnds(ItripTradeEnds itripTradeEnds)throws Exception;

    public Integer itriptxDeleteItripTradeEndsById(Long id)throws Exception;

    public Page<ItripTradeEnds> queryItripTradeEndsPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
