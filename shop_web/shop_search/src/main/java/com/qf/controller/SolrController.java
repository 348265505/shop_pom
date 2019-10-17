package com.qf.controller;

import com.qf.entity.Goods;
import com.qf.entity.Page;
import com.qf.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private ISearchService searchService;


    /**
     * 添加到solr的controller
     * @param goods
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public boolean insert(@RequestBody Goods goods){

       boolean b= searchService.insert(goods);


        return b ;
    }


    /**
     * controller的代码不要涉及业务的操作，所以在这里只应该进行交互，
     * 业务该放在业务层进行
     *
     * @return
     */
    @RequestMapping("/keyword")
    public String page(String keyword, Page page,Model model){
        //分页，还是创建一个page的实体类
        //查询的是商品信息，返回值应该是page

     /*  Page page1 =searchService.query(keyword,page);*/
        List<Goods> page1 = searchService.query(keyword,page);
        model.addAttribute("goodslist",page1);

         return "goodslist";
    }


}
