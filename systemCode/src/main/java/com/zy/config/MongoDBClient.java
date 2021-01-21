package com.zy.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class MongoDBClient {

    private  MongoClient mongoClient = null;
    private  String dbname = null;

    public MongoDBClient() {
//        try {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("mongodb");
//                String mongodbURL = resourceBundle.getString("mongodb.url");
//                if (mongodbURL == null || "".equals(mongodbURL.trim())) {
//                    mongoClient = new MongoClient();
//                } else {
                MongoClientURI clientURI = new MongoClientURI("mongodb://root:root@localhost:27017");
                mongoClient = new MongoClient(clientURI);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public String getDbname() {
        return dbname;
    }

    public void close(){
        mongoClient.close();
    }
}
