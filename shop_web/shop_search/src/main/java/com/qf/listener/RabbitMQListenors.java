package com.qf.listener;

import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListenors {


    @Autowired
    private ISearchService iSearchService;


        @RabbitListener(queues = "search_queue")
        public void s(Goods goods){
            System.out.println("接收到消息，并同步到素银库");
         iSearchService.insert(goods);
        }

}
