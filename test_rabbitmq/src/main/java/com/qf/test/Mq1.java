package com.qf.test;

import com.qf.until.Mqutil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 消息提供者
 */
public class Mq1 {
    /**
     * 使用第三方的技术，服务等第一步要先建立连接，而建立连接一般抽取成一个工具类
     *从工具类中获取连接对象
     *
     * 队列中的消息会轮流发送给所有的消费端，线程池中运行会打乱这个次序
     */
    public static void main(String[] args) throws IOException {

        //1.获得连接对象
        Connection connection = Mqutil.getConnection();
        //2.rabbitmq的所有操作都要基于通道channel来完成
        Channel channel = connection.createChannel();

        //3.通过通道声明一个队列
        channel.queueDeclare("myqueue",false,false,false,null);

        //4.发送消息,处理消息是单线程还是多线程呢？， 这时候就要用线程阻塞来测试
        for (int i = 0; i < 10; i++) {
            String info = "hello,rabbitmq"+i;
            channel.basicPublish("","myqueue",null,info.getBytes("utf-8"));

        }

        //5.关闭连接
        connection.close();
        System.out.println("成功发送消息到通道");

    }


}
