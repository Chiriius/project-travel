package com.miguel.project_travel.infraestructure.abstract_services;

import com.miguel.project_travel.api.models.responses.FlyResponse;

import java.util.Set;

public interface IFlyService extends CatalogService<FlyResponse> {
    Set<FlyResponse> readByOriginDestiny(String origin, String destiny);


}
