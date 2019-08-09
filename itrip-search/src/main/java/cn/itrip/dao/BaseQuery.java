package cn.itrip.dao;

import cn.itrip.common.Constants;
import cn.itrip.common.Page;
import cn.itrip.common.PropertiesUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("baseQuery")
public class BaseQuery<T> {
    private HttpSolrClient client=null;
    public BaseQuery(){
        String baseUrl = PropertiesUtils.get("database.properties", "baseUrl");
        //创建solrclient对象
        client = new HttpSolrClient.Builder(baseUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(3000)
                .build();
    }

    /**
     * 根据分页查询酒店列表
     * @param query
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Page<T> searchHotelByPage(SolrQuery query, Integer pageNo, Integer pageSize,Class<T> clazz) throws Exception{
        //判断如果页码或页码大小为0，给个默认值
        System.out.println(pageNo+"+++++"+pageSize);
        pageNo=pageNo==null? Constants.DEFAULT_PAGE_NO:pageNo;
        pageSize=pageSize==null?Constants.DEFAULT_PAGE_SIZE:pageSize;
        query.setStart((pageNo-1)*pageSize);
        query.setRows(pageSize);
        //执行查询
        QueryResponse response = client.query(query, SolrRequest.METHOD.GET);
        //总条目数
        long numFound = response.getResults().getNumFound();
        System.out.println("总记录数="+numFound);
        //分页查询的酒店列表
        List<T> list = response.getBeans(clazz);
        //封装分页数据
        Page page=new Page(pageNo,pageSize,(int)numFound);
        page.setRows(list);
        return page;
    }

    /**
     * 获取热门城市的默认酒店列表
     * @param query
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<T> searchHotCityHotelList(SolrQuery query, Class<T> clazz) throws Exception{
        return this.searchHotelByPage(query, 1, 6, clazz).getRows();
    }

}
