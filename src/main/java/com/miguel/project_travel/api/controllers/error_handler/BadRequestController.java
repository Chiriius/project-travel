package com.miguel.project_travel.api.controllers.error_handler;

import com.miguel.project_travel.api.models.responses.BaserErrorResponse;
import com.miguel.project_travel.api.models.responses.ErrorResponse;
import com.miguel.project_travel.api.models.responses.ErrorsResponse;
import com.miguel.project_travel.util.exceptions.IdNotFoundException;
import com.miguel.project_travel.util.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

//400 errores del cliente
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestController {

    @ExceptionHandler({IdNotFoundException.class, UserNotFoundException.class})
    public BaserErrorResponse handleIdNotFound(RuntimeException exception){

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaserErrorResponse handleIdNotFound(MethodArgumentNotValidException exception){
        var errors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));
        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();

    }
}
