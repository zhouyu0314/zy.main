package com.zy.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.zy.config.MongoDBClient;
import com.zy.dto.Dto;
import com.zy.dto.DtoUtil;
import com.zy.entity.Person;
import com.zy.service.MongoDemoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/mongo")
public class MongoDemoController {

    @Autowired(required = false)
    private MongoDemoService mongoDemoService;

    @Autowired(required = false)
    private MongoDBClient mongoDBClient;

    @Autowired
    private MongoTemplate mongoTemplate;
    @PostMapping("/find")
    public Dto find() {
        Query query = new Query(Criteria.where("name").is("张三").and("age").is(10));
        List<Person> all = mongoTemplate.find(query,Person.class,"person");
        return DtoUtil.returnSuccess("条件查询", all);
    }

    @PostMapping("/findAll")
    public Dto findAll() {

        List<String> all = mongoTemplate.findAll(String.class,"person");
        return DtoUtil.returnSuccess("查询全部", all);
    }


    @PostMapping("/insert")
    public Dto insert() {
        Person person = new Person("李四", 20);
        mongoTemplate.insert(person,"person");
        return DtoUtil.returnSuccess("插入成功!");
    }


    /**
     *         Map<String,Object> param = new HashMap<>();
     *         param.put("salary","1000");
     *         param.put("name","tom");
     *         param.put("age",100);
     *         param.put("address","usa");
     *         param.put("hobby","fuck");
     *         mongoTemplate.insert(param,"person");
     *         可以使用map 但是需要一个枚举类去维护字段名
     * @return
     */
    @PostMapping("/insertAll")
    public Dto insertAll() {
        List<Person> lists = new ArrayList<>();
        lists.add(new Person("王五", 20));
        lists.add(new Person("赵六", 30));
        lists.add(new Person("田七", 40));
        lists.add(new Person("老八", 50));
        mongoTemplate.insert(lists,"person");

        return DtoUtil.returnSuccess("插入成功!");
    }

    @PostMapping("/update")
    public Dto update(){


        Query query = new Query(Criteria.where("name").is("张三三").and("age").is(11));
        List<String> hobbies = new ArrayList<>();
        hobbies.add("唱歌");
        hobbies.add("跳舞");
        Update update = new Update();
        update.set("age", 110).set("address","江苏徐州");

        UpdateResult updateResult = mongoTemplate.updateMulti(query,update, "person");
        return DtoUtil.returnDataSuccess(updateResult);
    }

    @PostMapping("/updateInc")
    public Dto updateInc(){
        Query query = new Query(Criteria.where("name").is("张三三").and("age").is(110));
        Update update = new Update();
        update.inc("age",10);
        UpdateResult updateResult = mongoTemplate.updateMulti(query,update, "person");
        return DtoUtil.returnDataSuccess(updateResult);
    }







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
