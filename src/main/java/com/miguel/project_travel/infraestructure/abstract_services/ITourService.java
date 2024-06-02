package com.miguel.project_travel.infraestructure.abstract_services;

import com.miguel.project_travel.api.models.request.TourRequest;
import com.miguel.project_travel.api.models.responses.TourResponse;

import java.util.UUID;

public interface ITourService  extends SimpleCrudService<TourRequest, TourResponse, Long>{

    void removeTicket(Long tourId, UUID ticketId);
    UUID addTicket(Long flyId, Long tourId);

    void removeReservation(Long tourId, UUID reservationId);
    UUID addReservation(Long reservationId, Long tourId, Integer totalDays);


}
