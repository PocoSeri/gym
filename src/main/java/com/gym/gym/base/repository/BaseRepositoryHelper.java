package com.gym.gym.base.repository;

import com.gym.gym.utils.PageModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class BaseRepositoryHelper {
    public static String ASC_SORT_PREFIX = "+";
    public static String DESC_SORT_PREFIX = "-";

    public BaseRepositoryHelper() {
    }

    static Pageable getPageable(PageModel pageModel, List<String> allowedOrderByList) {
        List<Sort.Order> orderList = new ArrayList();
        if (pageModel == null) {
            pageModel = new PageModel();
        }

        if (pageModel.getSort() != null && pageModel.getSort().isEmpty() && !allowedOrderByList.isEmpty()) {
            pageModel.getSort().add((String)allowedOrderByList.get(0));
        }

        if (pageModel.getSort() != null) {
            Iterator var3 = pageModel.getSort().iterator();

            while(var3.hasNext()) {
                String sortField = (String)var3.next();
                Sort.Direction direction = Sort.Direction.ASC;
                if (sortField.startsWith(ASC_SORT_PREFIX)) {
                    direction = Sort.Direction.ASC;
                    sortField = sortField.substring(1);
                } else if (sortField.startsWith(DESC_SORT_PREFIX)) {
                    direction = Sort.Direction.DESC;
                    sortField = sortField.substring(1);
                }

                String sortFieldPattern = String.format("[%s%s]?%s", ASC_SORT_PREFIX, DESC_SORT_PREFIX, sortField);
                if (allowedOrderByList.stream().anyMatch((sortItem) -> {
                    return Pattern.matches(sortFieldPattern, sortItem);
                })) {
                    orderList.add(new Sort.Order(direction, sortField));
                }
            }
        }

        Sort sort = Sort.by(orderList);
        return PageRequest.of(pageModel.getPageStart() - 1, pageModel.getPageItems() > 0 ? pageModel.getPageItems() : 1, sort);
    }
}
