package com.qf.controller;


import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private IGoodsService goodsService;


    @RequestMapping("/list")
    @ResponseBody
    public List<Goods> s() {

        List<Goods> goodsList = goodsService.queryAll();

        return goodsList;
    }


    /**
     * 输入数据库
     *
     * @param goods
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public boolean goodsInsert(@RequestBody Goods goods) {

        int result = goodsService.insertGoods(goods);


        return result > 0;
    }

}
