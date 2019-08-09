package cn.itrip.service;

import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.dao.BaseQuery;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("searchHotelService")
public class SearchHotelServiceImpl implements SearchHotelService {
    @Resource(name = "baseQuery")
    private BaseQuery baseQuery;
    @Override
    public Page searchHotelListPage(SearchHotelVO searchHotelVO) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        //查询条件业务逻辑
//        构建全文检索条件

//        query.setQuery("destination:北京 AND keyword:大会堂");
        StringBuffer sb=new StringBuffer();
        sb.append("destination:"+searchHotelVO.getDestination());
        if (!EmptyUtils.isEmpty(searchHotelVO.getKeywords())) {
            sb.append(" AND keyword:"+searchHotelVO.getKeywords().replace(" ","|"));
        }
            query.setQuery(sb.toString());
            //检索商圈   ,3619,3620,
            //检索语句  (tradingAreaIds:*,3620,* OR tradingAreaIds:*,3619,*)
        String tradeAreaIds = searchHotelVO.getTradeAreaIds();
        if (EmptyUtils.isNotEmpty(tradeAreaIds)) {
            StringBuffer sb1=new StringBuffer("(");
            String[] ids = tradeAreaIds.split(",");
            boolean isFirst=true;
            for (String id : ids) {
                if (isFirst) {
                    sb1.append("tradingAreaIds:*,"+id+",*");
                    isFirst=false;
                }else{
                    sb1.append(" OR tradingAreaIds:*,"+id+",*");
                }
            }
            sb1.append(")");
            query.addFilterQuery(sb1.toString());
        }
        //价格区间
        if (searchHotelVO.getMinPrice()!=null) {
            query.addFilterQuery("maxPrice:["+searchHotelVO.getMinPrice() +" TO *]");
        }
        if (searchHotelVO.getMaxPrice()!=null) {
            query.addFilterQuery("minPrice:[* TO "+ searchHotelVO.getMaxPrice()+"]");
        }
        //星级
        if (searchHotelVO.getHotelLevel()!=null){
            query.addFilterQuery("hotelLevel:"+searchHotelVO.getHotelLevel());
        }
        //酒店特色
        String featureIds = searchHotelVO.getFeatureIds();
        if (EmptyUtils.isNotEmpty(featureIds)) {
            StringBuffer sb2=new StringBuffer("(");
            String[] ids = featureIds.split(",");
            boolean isFirst=true;
            for (String id : ids) {
                if (isFirst) {
                    sb2.append("featureIds:*,"+id+",*");
                    isFirst=false;
                }else{
                    sb2.append(" OR featureIds:*,"+id+",*");
                }
            }
            sb2.append(")");
            query.addFilterQuery(sb2.toString());
        }

 /*       StringBuilder sb = new StringBuilder();
        sb.append(" destination:"+searchHotelVO.getDestination());
        if (EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())) {
            // xxx ccc -> xxx|ccc
            String keyword = searchHotelVO.getKeywords().replace(' ', '|');
            sb.append(" AND keyword:"+keyword);
        }
        query.setQuery(sb.toString());

        // 构建过滤查询 fq条件
        // 商圈条件(a OR b OR c) AND
        if (EmptyUtils.isNotEmpty(searchHotelVO.getTradeAreaIds())) {
            // 123,222,333
            // ( tradingAreaIds:*,123,* OR tradingAreaIds:*,222,* OR tradingAreaIds:*,333,* )
            StringBuilder sb1 = new StringBuilder("(");
            String[] ids = searchHotelVO.getTradeAreaIds().split(",");
            boolean isFirst = true;
            for (String id : ids) {
                if (isFirst) {
                    sb1.append(" tradingAreaIds:*," + id + ",*");
                    isFirst = false;
                } else
                    sb1.append(" OR tradingAreaIds:*,"+id+",*");
            }
            sb1.append(")");
            query.addFilterQuery(sb1.toString());
        }
        // 用户输入500-800的搜索条件
        // 200 - 300//不符合搜索条件
        // 200 - 700
        // 200 - 1000
        // 600 - 700
        // 600 - 1000
        // 1000 - 2000 //不符合搜索条件
        if (EmptyUtils.isNotEmpty(searchHotelVO.getMaxPrice())) {
            query.addFilterQuery(" minPrice:[* TO "+searchHotelVO.getMaxPrice()+"]");
        }if (EmptyUtils.isNotEmpty(searchHotelVO.getMinPrice())) {
            query.addFilterQuery(" maxPrice:["+searchHotelVO.getMinPrice()+" TO *]");
        }
        // 酒店星级
        if (EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())) {
            query.addFilterQuery(" hotelLevel:" + searchHotelVO.getHotelLevel());
        }
        // 功能特色
        if (EmptyUtils.isNotEmpty(searchHotelVO.getFeatureIds())) {
            // 123,222,333
            // ( featureIds:*,123,* OR featureIds:*,222,* OR featureIds:*,333,* )
            StringBuilder sb2 = new StringBuilder("(");
            String[] ids = searchHotelVO.getFeatureIds().split(",");
            boolean isFirst = true;
            for (String id : ids) {
                if (isFirst) {
                    sb2.append(" featureIds:*," + id + ",*");
                    isFirst = false;
                } else
                    sb2.append(" OR featureIds:*,"+id+",*");
            }
            sb2.append(")");
            query.addFilterQuery(sb2.toString());
        }
        */
        //排序
        if (EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())) {
            query.addSort(searchHotelVO.getAscSort(), SolrQuery.ORDER.asc);
        }
        if (EmptyUtils.isNotEmpty(searchHotelVO.getDescSort())) {
            query.addSort(searchHotelVO.getDescSort(), SolrQuery.ORDER.desc);
        }
        return baseQuery.searchHotelByPage(query, searchHotelVO.getPageNo(),searchHotelVO.getPageSize(), ItripHotelVO.class);
    }

    @Override
    public List<ItripHotelVO> searchHotHotelList(String destination) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        query.setQuery("destination:"+destination);
       return baseQuery.searchHotCityHotelList(query,ItripHotelVO.class);
    }
}
