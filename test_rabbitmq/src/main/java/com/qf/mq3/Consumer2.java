package com.qf.mq3;

import com.qf.until.Mqutil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer2 {



    public static void main(String[] args) throws IOException {
        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare("myqueues",false,false,false,null);


        //在路由模式下队列可以绑定多个交换机
        channel.queueBind("myqueues","myExchanges","ooo");





        channel.basicConsume("myqueues",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费端2接受到消息"+new String(body,"utf-8"));
            }
        });



    }
}
