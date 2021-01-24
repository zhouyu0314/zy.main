//package com.zy.aop;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import com.zy.dto.Dto;
//import com.zy.dto.DtoUtil;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class MongoDBAspects {
//    private MongoClient mongoClient;
//    @Value("${mongodb.url}")
//    private String MONGODB_URL;
//
//
//    @Pointcut("")
//    public void pointCut() {
//    }
//
//    @Before("pointCut()")
//    public void before(JoinPoint joinPoint) {
//        MongoClientURI clientURI = new MongoClientURI(MONGODB_URL);
//        mongoClient = new MongoClient(clientURI);
//
//
//
//
//    }
//
//
//
//    @AfterReturning(value = "pointCut()",returning = "result")
//    public void afterReturning(JoinPoint joinPoint, Object result){
//        mongoClient.close();
//    }
//
//
//    @AfterThrowing(value = "pointCut()",throwing = "e")
//    public Dto afterReturning(JoinPoint joinPoint, RuntimeException e){
//        return DtoUtil.returnFail(e.getMessage(),"9999");
//    }
//
//}
