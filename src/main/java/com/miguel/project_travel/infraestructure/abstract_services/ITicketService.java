package com.miguel.project_travel.infraestructure.abstract_services;

import com.miguel.project_travel.api.models.request.TicketRequest;
import com.miguel.project_travel.api.models.responses.TicketResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITicketService extends CrudService <TicketRequest, TicketResponse, UUID>{

    BigDecimal findPrice(Long flyId );

}
