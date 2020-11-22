package com.fileManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileManagerApp {
    public static void main(String[] args) {
        SpringApplication.run(FileManagerApp.class,args);
    }
}
