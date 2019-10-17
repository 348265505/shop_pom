package com.qf.mq2;

import com.qf.until.Mqutil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class consumer1 {


    public static ExecutorService executors = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {

        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("myqueue3",false,false,false,null);
        //将交换机与队列绑定
        channel.queueBind("myqueue3","myExchange2","");

        //监听队列
        channel.basicConsume("myqueue3",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                executors.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("接受到fanout的信息"+ new String(body,"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });


    }

}
