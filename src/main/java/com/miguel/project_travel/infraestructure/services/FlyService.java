package com.miguel.project_travel.infraestructure.services;

import com.miguel.project_travel.api.models.responses.FlyResponse;
import com.miguel.project_travel.domain.entities.jpa.FlyEntity;
import com.miguel.project_travel.domain.repositories.jpa.FlyRepository;
import com.miguel.project_travel.infraestructure.abstract_services.IFlyService;
import com.miguel.project_travel.util.constants.CacheConstants;
import com.miguel.project_travel.util.enums.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class FlyService implements IFlyService {

    private final FlyRepository flyRepository;
    @Override
    public Set<FlyResponse> readByOriginDestiny(String origin, String destiny) {
        /*return this.flyRepository.selectOriginDestiny(origin,destiny)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());

         */
        return Set.of();
    }

    @Override
    public Page<FlyResponse> realAll(Integer page, Integer size, SortType sortType) {
        PageRequest pageRequest = null;
        switch (sortType){
            case NONE -> pageRequest=PageRequest.of(page,size);
            case LOWER -> pageRequest=PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case UPPER -> pageRequest=PageRequest.of(page,size,Sort.by(FIELD_BY_SORT).descending());

        }
        return this.flyRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    @Cacheable(value = CacheConstants.FLY_CACHE_NAME)
    public Set<FlyResponse> readLessPrice(BigDecimal price) {
       /*  try {
            Thread.sleep( 7000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

       return this.flyRepository.selectLessPrice(price)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());

       */
        return Set.of();

    }

    @Override
    @Cacheable(value = CacheConstants.FLY_CACHE_NAME)
    public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
     /*
     try {
            Thread.sleep( 7000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
     return this.flyRepository.selectBetweenPrice(min,max)
                .stream()
                .map(this::entityToResponse)
                .collect(Collectors.toSet());

      */
        return Set.of();
    }

    private FlyResponse entityToResponse(FlyEntity entity){
        FlyResponse response= new FlyResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
}
