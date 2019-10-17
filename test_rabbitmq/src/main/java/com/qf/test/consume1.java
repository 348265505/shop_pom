package com.qf.test;

import com.qf.until.Mqutil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class consume1 {


    //测试是否是单线程来处理mq的请求,由实验可知确实是单线程处理
    //那么在实际开发中，为了可以加快效率通常会用线程池来达到高并发处理中间键的请求

    //jdk自带的线程池,建立一个全局线程池
     public static ExecutorService executorService  = Executors.newCachedThreadPool();

    //客户端一样要获得连接对象
    public static void main(String[] args) throws IOException {


        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("myqueue",false,false,false,null);
        //消费端接收消息，提供端发送消息，消费端要怎么知道呢？ 答案是持续性的监听提供端



        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)  {

            //将消费代码放到线程池中运行
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("接受到中间键的消息"+ new String(body,"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                });


           /* 按顺序运行下来必定会阻塞线程

           try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }
        });

    }

}
