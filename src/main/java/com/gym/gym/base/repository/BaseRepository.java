package com.gym.gym.base.repository;

import com.gym.gym.base.model.BaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


@NoRepositoryBean
public interface BaseRepository<T extends BaseModel<ID>, ID extends Serializable> extends MongoRepository<T, ID> {

    Integer PAGE_SIZE_MAX = -1;
    Integer PAGE_SIZE_UNLIMITED = -2;

//    Page<T> findAllWithFilter(Class<T> typeParameterClass,
//                              Filtering filtering, Pageable pageable);

//    List<Object> getAllPossibleValuesForFilter(Class<T> typeParameterClass, Filtering filtering, String filterKey);

//    default Query constructQueryFromFiltering(Filtering filtering) {
//        Query query = new Query();
//        Map<String, Criteria> criteriaMap = new HashMap<>();
//        for (Filtering.Filter filter : filtering.getFilterList()) {
//            switch (filter.operator) {
//                case eq -> criteriaMap.put(filter.key, Criteria.where(filter.key).is(filter.value));
//                case gt -> {
//                    if (criteriaMap.containsKey(filter.key)) {
//                        criteriaMap.get(filter.key).gt(filter.value);
//                    } else {
//                        criteriaMap.put(filter.key, Criteria.where(filter.key).gt(filter.value));
//                    }
//                }
//                case gte -> {
//                    if (criteriaMap.containsKey(filter.key)) {
//                        criteriaMap.get(filter.key).gte(filter.value);
//                    } else {
//                        criteriaMap.put(filter.key, Criteria.where(filter.key).gte(filter.value));
//                    }
//                }
//                case in -> criteriaMap.put(filter.key, Criteria.where(filter.key).in((HashSet<Object>) filter.value));
//                case lt -> {
//                    if (criteriaMap.containsKey(filter.key)) {
//                        criteriaMap.get(filter.key).lt(filter.value);
//                    } else {
//                        criteriaMap.put(filter.key, Criteria.where(filter.key).lt(filter.value));
//                    }
//                }
//                case lte -> {
//                    if (criteriaMap.containsKey(filter.key)) {
//                        criteriaMap.get(filter.key).lte(filter.value);
//                    } else {
//                        criteriaMap.put(filter.key, Criteria.where(filter.key).lte(filter.value));
//                    }
//                }
//                case ne -> criteriaMap.put(filter.key, Criteria.where(filter.key).ne(filter.value));
//                case nin -> criteriaMap.put(filter.key, Criteria.where(filter.key).nin((HashSet<Object>) filter.value));
//                case regex -> criteriaMap.put(filter.key, Criteria.where(filter.key).regex((String) filter.value));
//                default -> throw new IllegalArgumentException("Unknown operator: " + filter.operator);
//            }
//        }
//        criteriaMap.values().forEach(query::addCriteria);
//        return query;
//    }

}
