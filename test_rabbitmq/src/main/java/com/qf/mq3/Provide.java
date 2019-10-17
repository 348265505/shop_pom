package com.qf.mq3;

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

        //声明一个交换机，参数为名字和（在这里是路由）模式
        channel.exchangeDeclare("myExchanges","direct");

        //发送消息给交换机，让交换机发送消息给队列


            String info ="hello , direct";
            channel.basicPublish("myExchanges","aaa",null,info.getBytes("utf-8"));


        connection.close();

        }


    }

