package com.gym.gym.base.repository;

import com.gym.gym.base.model.BaseModel;
import com.gym.gym.base.service.EmBaseRepository;
import com.gym.gym.exception.AppException;
import com.gym.gym.utils.FetchOptions;
import com.gym.gym.utils.PageModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.gym.gym.exception.AppException.ErrCode.IMPLEMENTATION_MISSING;
import static com.gym.gym.exception.AppException.ErrCode.QUERY_RESULT_TOO_LARGE;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel<ID>, ID extends Serializable> extends MongoRepository<T, ID>, EntityGraphQuerydslPredicateExecutor<T>, EmBaseRepository<T, ID> {

    Integer PAGE_SIZE_MAX = -1;
    Integer PAGE_SIZE_UNLIMITED = -2;

    default List<T> findAll(FetchOptions fetchOptions) throws AppException {
        return this.findAll(fetchOptions, Optional.empty());
    }

    default List<T> findAll(FetchOptions fetchOptions, Optional<List<ID>> idList) throws AppException {
        if (fetchOptions.getPageModel().getPageItems().equals(PAGE_SIZE_MAX)) {
            fetchOptions.getPageModel().setPageItems(QueryLimitConfig.QUERY_MAX_LIMIT);
        } else if (fetchOptions.getPageModel().getPageItems() > QueryLimitConfig.QUERY_MAX_LIMIT) {
            throw new AppException(QUERY_RESULT_TOO_LARGE, String.format("A query returned more than the max limit[%s] set by the system, this is not allowed and it indicates underlying problem. Please change query and/or use case to avoid this performance issue", QueryLimitConfig.QUERY_MAX_LIMIT));
        }

        Pageable pageable = BaseRepositoryHelper.getPageable(fetchOptions.getPageModel(), this.getAllowedOrderByList());
        BooleanBuilder builder = this.addFetchOptionsPredicate(new BooleanBuilder(), fetchOptions);

        List res;
        if (fetchOptions.getPageModel().getPageItems().equals(PAGE_SIZE_UNLIMITED)) {
            Iterable<T> resIt = this.findAll(fetchOptions);
            res = StreamSupport.stream(resIt.spliterator(), false).collect(Collectors.toList());
            fetchOptions.getPageModel().setTotalRows(res.size());
            return res;
        } else {
            Page<T> page =  this.findByKeys(builder, pageable);
            res =  page.getContent();
            if (page.getTotalElements() > (long)QueryLimitConfig.QUERY_MAX_LIMIT && page.getNumberOfElements() == QueryLimitConfig.QUERY_MAX_LIMIT) {
                throw new AppException(QUERY_RESULT_TOO_LARGE, String.format("A query returned more than the max limit[%s] set by the system, this is not allowed and it indicates underlying problem. Please change query and/or use case to avoid this performance issue", QueryLimitConfig.QUERY_MAX_LIMIT));
            } else {
                fetchOptions.getPageModel().setTotalRows((int)page.getTotalElements());
                return res;
            }
        }
    }

    default List<String> getAllowedOrderByList() {
        return new ArrayList<>(List.of("id"));
    }

    default BooleanBuilder addFetchOptionsPredicate(BooleanBuilder builder, FetchOptions fetchOptions) throws AppException {
        PageModel pageModel = fetchOptions.getPageModel();
        if (pageModel != null && pageModel.getF() != null && !pageModel.getF().isEmpty()) {
            throw new AppException(AppException.ErrCode.BAD_INPUT, "Filtering not supported for this entity. Please remove filter f[<any>]");
        } else {
            return builder;
        }
    }

    static <E extends Enum<E>> E getFilter(@NotNull Class<E> enumType, @NotNull Map.Entry<String, String> entry) {
        try {
            return Enum.valueOf(enumType, (String)entry.getKey());
        } catch (IllegalArgumentException var3) {
            throw new AppException(AppException.ErrCode.BAD_INPUT, String.format("Filter %s not found", entry.getKey()));
        }
    }

    default Page<T> findAllExtended(final Predicate predicate, final Pageable pageable) {
        return this.findAll(predicate, pageable);
    }

    default Page<ID> getKeyPage(BooleanBuilder builder, Pageable pageable) {
        Optional<Page<ID>> res = this.getKeyPageOpt(builder, pageable);
        if (res.isEmpty()) {
            Page<T> entityPage = this.findAllExtended(builder, pageable);
            List<ID> keyList = (List)StreamSupport.stream(entityPage.spliterator(), false).map(BaseModel::getId).collect(Collectors.toList());
            return new PageImpl(keyList, entityPage.getPageable(), entityPage.getTotalElements());
        } else {
            return res.get();
        }
    }

    default Page<T> findByKeys(BooleanBuilder builder, Pageable pageable) {
        Page<ID> keyPage = this.getKeyPage(builder, pageable);
        Optional<Predicate> keyInExpression = this.getIdInExpression(keyPage.getContent());
        if (keyInExpression.isEmpty()) {
            return this.findAllExtended(builder, pageable);
        } else {
            Predicate keyInPredicate = (new BooleanBuilder()).and((Predicate)keyInExpression.orElseThrow(() -> {
                return new AppException(IMPLEMENTATION_MISSING, "Id In filter");
            }));
            Iterable<T> resIt = this.findAll(keyInPredicate, pageable.getSort());
            List<T> entityList = (List)StreamSupport.stream(resIt.spliterator(), false).collect(Collectors.toList());
            return new PageImpl(entityList, keyPage.getPageable(), keyPage.getTotalElements());
        }
    }

    default Optional<Predicate> getIdInExpression(Collection<ID> ids) {
        return Optional.empty();
    }

    default boolean existsAllById(Set<ID> ids) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and((Predicate)this.getIdInExpression(ids).orElseThrow(() -> {
            return new AppException(IMPLEMENTATION_MISSING, "IdIn filter");
        }));

        return this.count(builder) == (long)ids.size();
    }

    static void missingFilterError(String filter) {
        throw new AppException(IMPLEMENTATION_MISSING, filter + " filter not implemented. Please contact support.");
    }

    default BooleanExpression filterLocalDateTimeByLocalDateStringValue(DateTimePath<LocalDateTime> localDateTimeDateTimePath, String dateValue) {
        LocalDateTime dtCreationDateEqValue = LocalDate.parse(dateValue).atStartOfDay();
        return localDateTimeDateTimePath.between(dtCreationDateEqValue, dtCreationDateEqValue.plusDays(1L).minusSeconds(1L));
    }
}
