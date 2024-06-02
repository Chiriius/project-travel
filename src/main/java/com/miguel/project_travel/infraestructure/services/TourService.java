package com.miguel.project_travel.infraestructure.services;

import com.miguel.project_travel.api.models.request.TourRequest;
import com.miguel.project_travel.api.models.responses.TourResponse;
import com.miguel.project_travel.domain.entities.*;
import com.miguel.project_travel.domain.repositories.CustomerRepository;
import com.miguel.project_travel.domain.repositories.FlyRepository;
import com.miguel.project_travel.domain.repositories.HotelRepository;
import com.miguel.project_travel.domain.repositories.TourRepository;
import com.miguel.project_travel.infraestructure.abstract_services.ITourService;
import com.miguel.project_travel.infraestructure.helpers.TourHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class TourService implements ITourService {

    private  final TourRepository tourRepository;
    private final FlyRepository flyRepository;
    private final HotelRepository hotelRepository;
    private  final CustomerRepository customerRepository;
    private final TourHelper tourHelper;
    @Override
    public void removeTicket(UUID ticketId, Long tourId) {

        var tourUpdate =this.tourRepository.findById(tourId).orElseThrow();
        tourUpdate.removeTicket(ticketId);
        this.tourRepository.save(tourUpdate);
    }

    @Override
    public UUID addTicket(Long flyId, Long tourId) {
        return null;
    }

    @Override
    public void removeReservation(UUID reservationId, Long tourId) {

    }

    @Override
    public UUID addReservation(Long reservationId, Long tourId) {
        return null;
    }

    @Override
    public TourResponse create(TourRequest request) {
        var customer= customerRepository.findById(request.getCustomerId()).orElseThrow();
        var flights= new HashSet<FlyEntity>();
        request.getFlights().forEach(fly->flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));
        var hotels= new HashMap<HotelEntity,Integer>();
        request.getHotels().forEach(hotel->hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()));

        var tourToSave = TourEntity.builder()
                .tickets(this.tourHelper.createTickets(flights,customer))
                .reservations(this.tourHelper.createReservations(hotels,customer))
                .customer(customer)
                .build();
        var tourSaved= this.tourRepository.save(tourToSave);
        return TourResponse.builder()
                .reservationIds(tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourSaved.getId())
                .build();
    }

    @Override
    public TourResponse read(Long id) {
        var tourFromDb=this.tourRepository.findById(id).orElseThrow();
        return TourResponse.builder()
                .reservationIds(tourFromDb.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
                .ticketIds(tourFromDb.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
                .id(tourFromDb.getId())
                .build();


    }

    @Override
    public void delete(Long id) {
        var tourToDelete= this.tourRepository.findById(id).orElseThrow();
        this.tourRepository.delete(tourToDelete);

    }
}
