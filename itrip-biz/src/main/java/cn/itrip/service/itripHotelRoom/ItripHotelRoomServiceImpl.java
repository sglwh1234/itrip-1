package cn.itrip.service.itripHotelRoom;
import cn.itrip.mapper.itripHotelRoom.ItripHotelRoomMapper;
import cn.itrip.pojo.ItripHotelRoom;
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
public class ItripHotelRoomServiceImpl implements ItripHotelRoomService {

    @Resource
    private ItripHotelRoomMapper itripHotelRoomMapper;

    public ItripHotelRoom getItripHotelRoomById(Long id)throws Exception{
        return itripHotelRoomMapper.getItripHotelRoomById(id);
    }

    public List<ItripHotelRoom>	getItripHotelRoomListByMap(Map<String,Object> param)throws Exception{
        return itripHotelRoomMapper.getItripHotelRoomListByMap(param);
    }

    public Integer getItripHotelRoomCountByMap(Map<String,Object> param)throws Exception{
        return itripHotelRoomMapper.getItripHotelRoomCountByMap(param);
    }

    public Integer itriptxAddItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception{
            itripHotelRoom.setCreationDate(new Date());
            return itripHotelRoomMapper.insertItripHotelRoom(itripHotelRoom);
    }

    public Integer itriptxModifyItripHotelRoom(ItripHotelRoom itripHotelRoom)throws Exception{
        itripHotelRoom.setModifyDate(new Date());
        return itripHotelRoomMapper.updateItripHotelRoom(itripHotelRoom);
    }

    public Integer itriptxDeleteItripHotelRoomById(Long id)throws Exception{
        return itripHotelRoomMapper.deleteItripHotelRoomById(id);
    }

    public Page<ItripHotelRoom> queryItripHotelRoomPageByMap(Map<String,Object> param,Integer pageNo,Integer pageSize)throws Exception{
        Integer total = itripHotelRoomMapper.getItripHotelRoomCountByMap(param);
        pageNo = EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO : pageNo;
        pageSize = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Page page = new Page(pageNo, pageSize, total);
        param.put("beginPos", page.getBeginPos());
        param.put("pageSize", page.getPageSize());
        List<ItripHotelRoom> itripHotelRoomList = itripHotelRoomMapper.getItripHotelRoomListByMap(param);
        page.setRows(itripHotelRoomList);
        return page;
    }

}
