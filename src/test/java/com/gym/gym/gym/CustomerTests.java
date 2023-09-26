package com.gym.gym.gym;

import com.gym.gym.entity.Customer;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ActiveProfiles("test")
public class CustomerTests {

    private final String COLLECTION_NAME = "customer";

    @Autowired MongoTemplate mongoTemplate;

    @BeforeEach
    public void clearDatabase() {
        // Delete all documents from the MongoDB collection before each test method
        mongoTemplate.dropCollection(COLLECTION_NAME);
    }

    @Test
    void postSuccess() {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("name", "FRANCO")
                .add("age", 41)
                .add("email", "test@test.id")
                .add("surname", "FRANCHINO").get();

        // we check that the Document has no rows in the DB
        List<Customer> customers = mongoTemplate.findAll(Customer.class, COLLECTION_NAME);
        assertThat(customers).isEmpty();

        //we save the DBObject and then we println for every row
        mongoTemplate.save(dbObject, COLLECTION_NAME);
        customers = mongoTemplate.findAll(Customer.class, COLLECTION_NAME);
        customers.forEach(System.out::println);

        // we check that there's only 1 "FRANCO"
        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).extracting("name").containsOnly("FRANCO");
    }

    @Test
    void putSuccess() {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("name", "FRANCO")
                .add("age", 41)
                .add("email", "test@test.id")
                .add("surname", "FRANCHINO").get();

        mongoTemplate.save(dbObject, COLLECTION_NAME);

        ObjectId objectId = (ObjectId) dbObject.get("id");

        // Simulate the PUT request by updating the email field
        Query query = new Query(Criteria.where("id").is(objectId));
        Update update = new Update().set("email", "new.email@example.com");
        mongoTemplate.updateFirst(query, update, COLLECTION_NAME);

        // Assert the updated state
        DBObject updatedObject = mongoTemplate.findOne(query, DBObject.class, COLLECTION_NAME);
        assertThat(updatedObject).isNotNull();
        assertThat(updatedObject.get("email")).isEqualTo("new.email@example.com");

        assertThat(mongoTemplate.findAll(DBObject.class, COLLECTION_NAME)).extracting("name").containsOnly("FRANCO");
    }

    @Test
    void deleteSuccess() {
        DBObject dbObject = BasicDBObjectBuilder.start()
                .add("name", "FRANCO")
                .add("age", 41)
                .add("email", "test@test.id")
                .add("surname", "FRANCHINO").get();

        mongoTemplate.save(dbObject, COLLECTION_NAME);

        ObjectId objectId = (ObjectId) dbObject.get("id");

        // Simulate the DELETE request by deleting the document based on _id
        Query query = new Query(Criteria.where("id").is(objectId));
        mongoTemplate.remove(query, COLLECTION_NAME);

        // we check that the Document has no rows in the DB
        List<Customer> customers = mongoTemplate.findAll(Customer.class, COLLECTION_NAME);
        assertThat(customers).isEmpty();
    }
}
