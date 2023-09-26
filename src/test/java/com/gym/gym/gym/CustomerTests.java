package com.gym.gym.gym;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureDataMongo
public class CustomerTests {

    private final String COLLECTION_NAME = "customer";

    @Autowired MongoTemplate mongoTemplate;

    @Test
    void postSuccess() {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("name", "FRANCO")
                .add("age", 41)
                .add("email", "test@test.id")
                .add("surname", "FRANCHINO").get();
        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).isEmpty();

        mongoTemplate.save(dbObject, COLLECTION_NAME);

        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).extracting("name").containsOnly("FRANCO");
    }
}
