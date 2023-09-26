package com.gym.gym.base.model.restresponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PaginatedResponse<T> {
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private List<T> output;
    private boolean hasNext;
}
