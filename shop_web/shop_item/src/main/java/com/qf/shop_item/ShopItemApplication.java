package com.qf.shop_item;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.qf")
@EnableEurekaClient
public class ShopItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopItemApplication.class, args);
    }


    @Bean("item_queue")
    public Queue getItemQueue(){

        return new Queue("item_queue");
    }

}
