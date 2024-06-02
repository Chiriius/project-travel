package com.miguel.project_travel.api.models.responses;

import com.miguel.project_travel.util.enums.AeroLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse implements Serializable {

    private  Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private BigDecimal price;
    private String origin_name;
    private String destiny_name;
    private AeroLine aeroLine;

}
