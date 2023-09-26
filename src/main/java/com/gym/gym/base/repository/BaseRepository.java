package com.gym.gym.base.repository;

import com.gym.gym.base.model.BaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface BaseRepository<T extends BaseModel<ID>, ID extends Serializable> extends MongoRepository<T, ID> {

}
