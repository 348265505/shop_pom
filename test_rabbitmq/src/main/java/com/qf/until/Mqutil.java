package com.qf.until;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Mqutil {

    //先在外层声明一个连接工厂对象，工厂对象一般只创建一次，通过方法供使用者调用
    private static ConnectionFactory connectionFactory;

    //怎么保证对象只创建一次呢？
    //可以采用根据类加载机制完成
    //静态代码块随着类的加载而加载，并且只加载一次
    //非静态的代码块？
    //非静态的属性 ，静态的属性
    //因此在这里把创建连接对象放到静态代码块中执行，达到该对象只创建一次的目的
    static {
        //创建连接工厂
        connectionFactory = new ConnectionFactory();
        //地址
        connectionFactory.setHost("192.168.68.200");
        //虚拟主机
        connectionFactory.setVirtualHost("/admin");
        connectionFactory.setPort(5672);
        connectionFactory.setPassword("admin");
        connectionFactory.setUsername("admin");
    }

    //创建完工厂对象后就需要创建连接对象供使用者调用
    public static Connection getConnection (){
        Connection connection =null;


        try {
            connection=connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        return connection;
    }

}
