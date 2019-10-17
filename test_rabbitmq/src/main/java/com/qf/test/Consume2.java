package com.qf.test;

import com.qf.until.Mqutil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consume2 {


    //获取线程池对象
    public static ExecutorService executors = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        //获取mq的连接对象
        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();

        //声明一个队列
        channel.queueDeclare("myqueue",false,false,false,null);

        channel.basicConsume("myqueue",true,new DefaultConsumer(channel){




            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {


                //把消息放到线程池中进行并发处理
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("接受到myqueue2的信息"+new String(body,"utf-8"));
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

            }
        });

    }
}
