package com.gym.gym.base.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityGraphQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    Optional<T> findOne(Predicate predicate);

    Iterable<T> findAll(Predicate predicate);

    Iterable<T> findAll(Predicate predicate, Sort sort);

    Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    Iterable<T> findAll( OrderSpecifier<?>... orders);

    Page<T> findAll(Predicate predicate, Pageable pageable);
}
