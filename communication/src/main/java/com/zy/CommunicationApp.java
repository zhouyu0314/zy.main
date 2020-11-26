package com.zy;

import com.zy.server.CommunicationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CommunicationApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CommunicationApp.class, args);
        run.getBean(CommunicationServer.class).run();

    }

}
