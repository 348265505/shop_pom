package com.qf.shop_goods;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQController {

    /**
     * 配置信息已经写好了，现在要创建队列
     */

    @Bean("search_queue")
    public Queue getQueue(){
        return new Queue("search_queue");
    }

    @Bean("item_queue")
    public Queue getQueue2(){
        return new Queue("item_queue");
    }


    /**
     * 创建交换机 广播模式即可,一台交换机即可，在
     * goods服务中，添加商品时，向交换机发送goods对象给item和search服务
     *
     */

    @Bean("goods_exchange")
    public FanoutExchange getfanoutExchane(){

        return new FanoutExchange("goods_exchange");

    }


    /**
     *
     * @param search_queue  传入的参数
     * @param goods_exchange
     * @return
     */

    @Bean("bind_search")
    public Binding getBinding_search(Queue search_queue,FanoutExchange goods_exchange){

        return BindingBuilder.bind(search_queue).to(goods_exchange);
    }


    public Binding getBinding_itemm(Queue item_queue,FanoutExchange goods_exchange){
        return BindingBuilder.bind(item_queue).to(goods_exchange);
    }

}
