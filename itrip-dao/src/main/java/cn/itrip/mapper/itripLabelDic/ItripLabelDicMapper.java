package cn.itrip.mapper.itripLabelDic;
import cn.itrip.beans.pojo.ItripLabelDic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripLabelDicMapper {

	public ItripLabelDic getItripLabelDicById(@Param(value = "id") Long id)throws Exception;

	public List<ItripLabelDic>	getItripLabelDicListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripLabelDicCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer updateItripLabelDic(ItripLabelDic itripLabelDic)throws Exception;

	public Integer deleteItripLabelDicById(@Param(value = "id") Long id)throws Exception;

}
