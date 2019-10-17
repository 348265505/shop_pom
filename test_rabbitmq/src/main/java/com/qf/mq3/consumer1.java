package com.qf.mq3;

import com.qf.until.Mqutil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class consumer1 {



    public static void main(String[] args) throws IOException {

        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("myqueues",false,false,false,null);
        //将交换机与队列绑定
        channel.queueBind("myqueues","myExchanges","aaa");
        channel.queueBind("myqueues","myExchanges","ccc");
        channel.queueBind("myqueues","myExchanges","eee");

        //监听队列
            channel.basicConsume("myqueues",true,new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("消费端1接受到消息"+new String(body,"utf-8"));
                }
            });

    }

}
