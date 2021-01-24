package com.zy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.zy.config.MongoDBClient;
import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.service.MongoDemoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/mongo")
public class MongoDemoController {

    @Autowired(required = false)
    private MongoDemoService mongoDemoService;

    @Autowired(required = false)
    private MongoDBClient mongoDBClient;




    @GetMapping("/showAll")
    public Dto showAll() {
        MongoCollection<Document> mongoClient = mongoDBClient.getMongoClient("zy", "zb");
        FindIterable<Document> documents = mongoClient.find().limit(3);
        List<Document> lists = new ArrayList<>();
        for (Document document : documents) {
            lists.add(document);
        }
        mongoDBClient.close();
        return DtoUtil.returnDataSuccess(lists);
    }

    @GetMapping("/testEhcache/{param}")
    public Dto testEhcache(@PathVariable("param") String param) {
        String test = mongoDemoService.test(param);
        return DtoUtil.returnDataSuccess(test);
    }


}
