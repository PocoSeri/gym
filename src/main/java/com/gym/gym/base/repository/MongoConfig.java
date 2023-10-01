package com.gym.gym.base.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig {

    @Value("${mongodb.connection.host}")
    private String hostName;

    @Value("${mongodb.connection.databaseName}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(hostName);
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(databaseName);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }
}






