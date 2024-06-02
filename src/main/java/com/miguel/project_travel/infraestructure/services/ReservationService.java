package com.miguel.project_travel.infraestructure.services;

import com.miguel.project_travel.api.models.request.ReservationRequest;
import com.miguel.project_travel.api.models.responses.HotelResponse;
import com.miguel.project_travel.api.models.responses.ReservationResponse;
import com.miguel.project_travel.domain.entities.ReservationEntity;
import com.miguel.project_travel.domain.repositories.CustomerRepository;
import com.miguel.project_travel.domain.repositories.HotelRepository;
import com.miguel.project_travel.domain.repositories.ReservationRepository;
import com.miguel.project_travel.infraestructure.abstract_services.IReservationService;
import com.miguel.project_travel.infraestructure.helpers.BlackListHelper;
import com.miguel.project_travel.infraestructure.helpers.CustomerHelper;
import com.miguel.project_travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

    private final CustomerRepository customerRepository;
    private final HotelRepository hotelRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerHelper customerHelper;
    private BlackListHelper blackListHelper;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow(()-> new IdNotFoundException("customer"));

        var reservationToPersist= ReservationEntity.builder()
                .id(UUID.randomUUID())
                .hotel(hotel)
                .totalDays(request.getTotalDays())
                .dateTimeReservation(LocalDateTime.now())
                .dateStart(LocalDate.now())
                .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
                .customer(customer)
                .price(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)))
                .build();
        var reservationPersisted= reservationRepository.save(reservationToPersist);
        this.customerHelper.incrase(customer.getDni(),ReservationService.class);
        return this.entityToResponse(reservationPersisted);
    }

    @Override
    public ReservationResponse read(UUID id) {
        var reservationFromDB= this.reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));
        return this.entityToResponse(reservationFromDB);
    }

    @Override
    public ReservationResponse update(ReservationRequest request, UUID id) {

        var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow(()-> new IdNotFoundException("hotel"));

        var reservationToUpdate= this.reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));

        reservationToUpdate.setHotel(hotel);
        reservationToUpdate.setTotalDays(request.getTotalDays());
        reservationToUpdate.setDateTimeReservation(LocalDateTime.now());
        reservationToUpdate.setDateStart(LocalDate.now());
        reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
        reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage)));

        var reservationUpdated=this.reservationRepository.save(reservationToUpdate);
        log.info("Reservation updated with id {}",reservationUpdated.getId());

        return this.entityToResponse(reservationUpdated);


    }

    @Override
    public void delete(UUID id) {

        var reservationToDelete = reservationRepository.findById(id).orElseThrow(()-> new IdNotFoundException("reservation"));
        this.reservationRepository.delete(reservationToDelete);
    }


    private ReservationResponse entityToResponse(ReservationEntity entity){
        var response = new ReservationResponse();
        BeanUtils.copyProperties(entity,response);
        var hotelResponse = new HotelResponse();
        BeanUtils.copyProperties(entity.getHotel(), hotelResponse);
        response.setHotel(hotelResponse);
        return response;
    }

    public static final BigDecimal charges_price_percentage=BigDecimal.valueOf(0.20);

    @Override
    public BigDecimal findPrice(Long hotelId) {
        var hotel= this.hotelRepository.findById(hotelId).orElseThrow(()-> new IdNotFoundException("hotel"));
        return hotel.getPrice().add(hotel.getPrice().multiply(charges_price_percentage));

    }

}
