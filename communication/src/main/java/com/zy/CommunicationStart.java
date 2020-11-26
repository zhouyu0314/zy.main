//package com.zy;
//
//import com.zy.server.CommunicationServer;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CommunicationStart implements ApplicationListener<ContextRefreshedEvent> {
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        System.out.println("启动webstocket.....");
//        CommunicationServer.getInstnal().run();
//    }
//}
