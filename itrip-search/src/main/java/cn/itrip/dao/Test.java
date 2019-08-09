package cn.itrip.dao;

import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.common.PropertiesUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException, SolrServerException {
        String baseUrl = PropertiesUtils.get("database.properties", "baseUrl");
        //创建solrclient对象
        HttpSolrClient client = new HttpSolrClient.Builder(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(3000)
                .build();
        //创建查询条件
        SolrQuery query = new SolrQuery("*:*");
//        query.setQuery("keyword:北京");
//        query.setFilterQueries("keyword:北京");
        //客户查询范围500-800
//        query.setFilterQueries("minPrice:[* TO 800] AND maxPrice:[500 TO *]");//等同于下面两行
        query.addFilterQuery("minPrice:[* TO 800]");
        query.addFilterQuery("maxPrice:[500 TO *]");


        query.setStart(0);
        query.setRows(100);
        query.setSort("id", SolrQuery.ORDER.desc);
        query.setFields("id","hotelName","minPrice","maxPrice");
        //执行查询
        QueryResponse response = client.query(query, SolrRequest.METHOD.GET);
        //获取查询结果
        SolrDocumentList list = response.getResults();
        List<ItripHotelVO> beans = response.getBeans(ItripHotelVO.class);
        System.out.println("beans.size()="+beans.size());
        System.out.println("hotelname-----"+beans.get(0).getHotelName());
        //解析查询结果
        long numFound = list.getNumFound();
        System.out.println("条目数："+numFound);
        for (SolrDocument doc : list) {
//            Collection<String> fieldNames = doc.getFieldNames();
//            System.out.println(fieldNames);
            Object id = doc.get("id");
            Object hotelName = doc.get("hotelName");
            Object minPrice = doc.get("minPrice");
            Object maxPrice = doc.get("maxPrice");
            System.out.println("id="+id+",hotelName="+hotelName+",minPrice="+minPrice+",maxPrice"+maxPrice);
        }

    }
}
