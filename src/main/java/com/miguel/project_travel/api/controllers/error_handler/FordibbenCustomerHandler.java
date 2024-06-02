package com.miguel.project_travel.api.controllers.error_handler;

import com.miguel.project_travel.api.models.responses.BaserErrorResponse;
import com.miguel.project_travel.api.models.responses.ErrorResponse;
import com.miguel.project_travel.util.exceptions.ForbiddenCustomerException;
import com.miguel.project_travel.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FordibbenCustomerHandler {

    @ExceptionHandler(ForbiddenCustomerException.class)
    public BaserErrorResponse handleIdNotFound(ForbiddenCustomerException exception){

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }
}
