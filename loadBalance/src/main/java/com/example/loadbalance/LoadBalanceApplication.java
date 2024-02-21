package com.example.loadbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class LoadBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalanceApplication.class, args);
    }

}
