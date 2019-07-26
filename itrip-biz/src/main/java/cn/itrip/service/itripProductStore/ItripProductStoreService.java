package cn.itrip.service.itripProductStore;
import cn.itrip.pojo.ItripProductStore;
import java.util.List;
import java.util.Map;
import java.util.List;
import java.util.Map;
import cn.itrip.common.Page;
/**
*
*/
public interface ItripProductStoreService {

    public ItripProductStore getItripProductStoreById(Long id)throws Exception;

    public List<ItripProductStore>	getItripProductStoreListByMap(Map<String,Object> param)throws Exception;

    public Integer getItripProductStoreCountByMap(Map<String,Object> param)throws Exception;

    public Integer itriptxAddItripProductStore(ItripProductStore itripProductStore)throws Exception;

    public Integer itriptxModifyItripProductStore(ItripProductStore itripProductStore)throws Exception;

    public Integer itriptxDeleteItripProductStoreById(Long id)throws Exception;

    public Page<ItripProductStore> queryItripProductStorePageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception;
}
