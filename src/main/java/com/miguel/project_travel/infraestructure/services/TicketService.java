package com.miguel.project_travel.infraestructure.services;

import com.miguel.project_travel.api.models.request.TicketRequest;
import com.miguel.project_travel.api.models.responses.FlyResponse;
import com.miguel.project_travel.api.models.responses.TicketResponse;
import com.miguel.project_travel.domain.entities.TicketEntity;
import com.miguel.project_travel.domain.repositories.CustomerRepository;
import com.miguel.project_travel.domain.repositories.FlyRepository;
import com.miguel.project_travel.domain.repositories.TicketRepository;
import com.miguel.project_travel.infraestructure.abstract_services.ITicketService;
import com.miguel.project_travel.infraestructure.helpers.BlackListHelper;
import com.miguel.project_travel.infraestructure.helpers.CustomerHelper;
import com.miguel.project_travel.infraestructure.helpers.EmailHelper;
import com.miguel.project_travel.util.enums.BesTravelUtil;
import com.miguel.project_travel.util.enums.Tables;
import com.miguel.project_travel.util.exceptions.IdNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

    private final FlyRepository flyRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final CustomerHelper customerHelper;
    private final BlackListHelper blackListHelper;
    private final EmailHelper emailHelper;

    @Override
    public TicketResponse create(TicketRequest request) {
        blackListHelper.isInBlackListCustomer(request.getIdClient());
        var fly =flyRepository.findById(request.getIdFly()).orElseThrow(()->new IdNotFoundException(Tables.fly.name()));
        var customer = customerRepository.findById(request.getIdClient()).orElseThrow(()->new IdNotFoundException(Tables.customer.name()));
        var ticketToPersist = TicketEntity.builder()
                .id(UUID.randomUUID())
                .fly(fly)
                .customer(customer)
                .price(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)))
                .purchaseDate(LocalDate.now())
                .arrivalDate(BesTravelUtil.getRandomLatter())
                .departureDate(BesTravelUtil.getRandomSoon())
                .build();

        var ticketPersisted = this.ticketRepository.save(ticketToPersist);

        customerHelper.incrase(customer.getDni(),TicketService.class);
        if (Objects.isNull(request.getEmail()))this.emailHelper.sendMail(request.getEmail(),customer.getFullName(), Tables.ticket.name());
        log.info("Ticket saved with id:{}",ticketPersisted.getId());

        return  this.entityToResponse(ticketPersisted);
    }

    @Override
    public TicketResponse read(UUID id) {
        var ticketFromDB= this.ticketRepository.findById(id).orElseThrow();
        return this.entityToResponse(ticketFromDB);
    }

    @Override
    public TicketResponse update(TicketRequest request, UUID id) {

        var ticketToUpdate = ticketRepository.findById(id).orElseThrow();
        var fly =flyRepository.findById(request.getIdFly()).orElseThrow();

        ticketToUpdate.setFly(fly);
        ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage)));
        ticketToUpdate.setDepartureDate(BesTravelUtil.getRandomSoon());
        ticketToUpdate.setArrivalDate(BesTravelUtil.getRandomLatter());

        var ticketUpdated=this.ticketRepository.save(ticketToUpdate);

        log.info("Ticket updated with id {}",ticketUpdated.getId());

        return this.entityToResponse(ticketUpdated);
    }

    @Override
    public void delete(UUID id) {
        var ticketToDelete = ticketRepository.findById(id).orElseThrow();
        this.ticketRepository.delete(ticketToDelete);

    }

    private TicketResponse entityToResponse(TicketEntity entity){
        var response = new TicketResponse();
        BeanUtils.copyProperties(entity,response);
        var flyResponse = new FlyResponse();
        BeanUtils.copyProperties(entity.getFly(), flyResponse);
        response.setFly(flyResponse);
        return response;
    }

    @Override
    public BigDecimal findPrice(Long flyId) {
        var fly= this.flyRepository.findById(flyId).orElseThrow();
        return fly.getPrice().add(fly.getPrice().multiply(charger_price_percentage));

    }

    public static final BigDecimal charger_price_percentage=BigDecimal.valueOf(0.25);
}
