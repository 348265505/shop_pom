package com.qf;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Redis {


    public static void main(String[] args) {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);
        config.setMaxIdle(200);

        JedisPool jedisPool = new JedisPool();


        //获得jedis对象
        Jedis jedis = jedisPool.getResource();

        //通过jedis操作对象
        jedis.set("name", "xiaoming");
        String name = jedis.get("name");
        System.out.println(name);


        jedis.close();


    }
}
