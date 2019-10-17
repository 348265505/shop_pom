package com.qf.shop_search;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.qf")
@EnableEurekaClient
public class ShopSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopSearchApplication.class, args);
    }


    @Bean("search_queue")
    public Queue getSearchQueue(){
        return new Queue("search_queue");
    }
}
