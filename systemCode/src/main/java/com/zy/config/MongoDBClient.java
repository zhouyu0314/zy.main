package com.zy.config;

import com.mongodb.client.MongoCollection;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBClient {

    private MongoClient mongoClient;
    @Value("${mongodb.url}")
    private String MONGODB_URL;

    public MongoCollection<Document> getMongoClient(String DBName,String collectionName) {
        MongoClientURI clientURI = new MongoClientURI(MONGODB_URL);
        mongoClient = new MongoClient(clientURI);
        return mongoClient.getDatabase(DBName).getCollection(collectionName);


    }

    public void close() {
        mongoClient.close();
    }

}
