package com.zy;

import com.zy.config.MongoDBClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class SystemCodeApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemCodeApp.class, args);

    }

}
