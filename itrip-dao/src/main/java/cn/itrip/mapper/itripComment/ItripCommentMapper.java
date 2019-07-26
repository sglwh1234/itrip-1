package cn.itrip.mapper.itripComment;
import cn.itrip.pojo.ItripComment;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ItripCommentMapper {

	public ItripComment getItripCommentById(@Param(value = "id") Long id)throws Exception;

	public List<ItripComment>	getItripCommentListByMap(Map<String,Object> param)throws Exception;

	public Integer getItripCommentCountByMap(Map<String,Object> param)throws Exception;

	public Integer insertItripComment(ItripComment itripComment)throws Exception;

	public Integer updateItripComment(ItripComment itripComment)throws Exception;

	public Integer deleteItripCommentById(@Param(value = "id") Long id)throws Exception;

}
