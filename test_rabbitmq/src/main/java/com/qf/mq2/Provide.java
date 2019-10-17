package com.qf.mq2;

import com.qf.until.Mqutil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 发送消息给交换机，消息会即时丢失，以为交换机不具备存储数据的功能
 *
 */
public class Provide {

    public static void main(String[] args) throws IOException {
        Connection connection = Mqutil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("myqueue3",false,false,false,null);

        //声明一个交换机，参数为名字和（在这里是广播）模式
        channel.exchangeDeclare("myExchange2","fanout");

        //发送消息给交换机，让交换机发送消息给队列

        for (int i = 0; i < 10; i++) {
            String info ="hello , direct"+ i;
            channel.basicPublish("myExchange2","",null,info.getBytes("utf-8"));

        }

        connection.close();
        System.out.println("消息发送完成");
    }
}
