package com.gym.gym.repository;


import com.gym.gym.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserModelRepository extends MongoRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
