package com.qf.controller;

import com.qf.entity.Goods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItenController {

    @Autowired
    private Configuration configuration;


    /**
     * 生成静态页面
     * @return
     */
  /*   @RequestMapping("/createHtml")
    @ResponseBody
   public boolean createHtml(@RequestBody Goods g){
        //准备一个输入路径
        //获得classpath路径
        String path = ItenController.class.getResource("/static/Html").getPath();
        System.out.println("生成静态页的路径: "+path);

        //通过freemark生成静态页面

        try ( Writer fileWriter = new FileWriter(path+"/"+g.getId()+".html");
        ){
            *//**
             * 生成模板对象 需要两个条件，一是页面；二是数据
             *//*

            //获得模板对象
            Template template = configuration.getTemplate("goods.ftl");

            //准备数据
            Map<String,Object> map = new HashMap<>();

            map.put("goods",g);

            //生成静态页面
            template.process(map,fileWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }*/

}
