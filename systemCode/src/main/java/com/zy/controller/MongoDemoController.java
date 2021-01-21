package com.zy.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.zy.config.MongoDBClient;
import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.service.MongoDemoService;
import feign.Param;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mongo")
public class MongoDemoController {

    @Autowired(required = false)
    private MongoDemoService mongoDemoService;

    @Autowired(required = false)
    private MongoDBClient mongoDBClient;


    @GetMapping("/showAll")
    public Dto showAll() {
        MongoClient mongoClient = mongoDBClient.getMongoClient();
        //MongoIterable<String> list = mongoClient.listDatabaseNames();

        MongoCollection<Document> collection = mongoClient.getDatabase("zy").getCollection("zb");
        collection.insertOne(new Document("name", "Deng").append("Gender", "Male").append("Tel", "18600000000"));

        FindIterable<Document> documents = collection.find();
        mongoClient.close();
        return DtoUtil.returnDataSuccess(documents);
    }

    @GetMapping("/testEhcache/{param}")
    public Dto testEhcache(@PathVariable("param") String param) {
        String test = mongoDemoService.test(param);
        return DtoUtil.returnDataSuccess(test);
    }


}
