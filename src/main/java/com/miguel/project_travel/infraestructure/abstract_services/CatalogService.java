package com.miguel.project_travel.infraestructure.abstract_services;

import com.miguel.project_travel.util.SortType;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Set;

public interface CatalogService <R> {

    Page<R>realAll(Integer page, Integer size, SortType sortType);

    Set<R> readLessPrice(BigDecimal price);

    Set<R> readBetweenPrice(BigDecimal min,BigDecimal max);

    String FIELD_BY_SORT = "price";

}
