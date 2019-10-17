package com.qf.test_solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolrApplicationTests {


    @Autowired
    private SolrClient solrClient;


    @Test
    public void contextLoads() {




    }


    @Test
    public void ss(){
        SolrInputDocument solrInputFields = new SolrInputDocument();
        //因为是string类型所以要 +""
        solrInputFields.addField("id",66+ "");
        solrInputFields.addField("save",555);
        solrInputFields.addField("subject","欧克");
        solrInputFields.addField("price",34);
        solrInputFields.addField("info","声卡的绝杀");

        //处理封面
        solrInputFields.addField("images","www.baidu");

        //添加到搜索库
        try {
            solrClient.add(solrInputFields);
            solrClient.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void s(){
        SolrInputDocument solrInputFields = new SolrInputDocument();
        try {
            UpdateResponse updateResponse = solrClient.deleteByQuery("66");
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
