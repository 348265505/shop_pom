package com.qf.service.serverimpl;


import com.qf.dao.GoodsImgMapper;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.entity.GoodsImage;
import com.qf.feign.ItemFeign;
import com.qf.feign.SearchFeign;
import com.qf.service.IGoodsService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {


    @Autowired
    private GoodsMapper goodsMapper;


    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Autowired
    private SearchFeign searchFeign;

    @Autowired
    private ItemFeign itemFeign;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @Override
    @Transactional
    public int insertGoods(Goods goods) {
        //保存商品信息
        goodsMapper.insert(goods);

        //保存商品图片

        //封装一个封面对象
        GoodsImage fengmian = new GoodsImage(
                goods.getId(),
                null,
                goods.getFengmian(),
                1
        );

        goodsImgMapper.insert(fengmian);

        //保存其他图片
        for (String otherUrl : goods.getOtherImg()) {
            GoodsImage otherImg = new GoodsImage(
                    goods.getId(),
                    null,
                    otherUrl,
                    0
            );

            goodsImgMapper.insert(otherImg);
        }


      /*  //调用搜索服务将最新的商品添加到solr索引库中
        if (!searchFeign.insert(goods))
               throw new  RuntimeException("索引库添加失败");

        //feign的作用在这里是传值的作用
        itemFeign.createHtml(goods);
*/

      rabbitTemplate.convertAndSend("goods_exchange", "", goods);

        return 1;


    }

    @Override
    public List<Goods> queryAll() {
        List<Goods> goodsList = goodsMapper.queryAllGoods();
        return goodsList;
    }
}
