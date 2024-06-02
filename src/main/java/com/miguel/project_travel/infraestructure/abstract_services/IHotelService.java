package com.miguel.project_travel.infraestructure.abstract_services;

import com.miguel.project_travel.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelService extends CatalogService<HotelResponse>{
    Set<HotelResponse> readByRating(Integer rating);
}
