package com.miguel.project_travel.api.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ReservationRequest implements Serializable {

    @Size(min = 18,max = 20,message = "El tamaño de caracteres debe ser entre 18 y 20 caracteres")
    @NotBlank(message = "La id del cliente es obligaroia")
    private String idClient;
    @Positive
    private Long idHotel;

    @Min(value = 1, message = "El minimo de dias de hospedaje es 1")
    @Max(value = 30, message = "El maximo de dias que se puede hospedar son 30")
    private Integer totalDays;
    @Email(message = "Email invalido")
    private String email;
}
