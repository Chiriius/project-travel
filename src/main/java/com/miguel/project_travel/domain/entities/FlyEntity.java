package com.miguel.project_travel.domain.entities;

import com.miguel.project_travel.util.AeroLine;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity (name = "fly")
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Data
@Builder

public class FlyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Double originLat;
    private Double originLng;
    private Double destinyLat;
    private Double destinyLng;
    private BigDecimal price;
    @Column(length = 20)
    private String origin_name;
    @Column(length = 20)
    private String destiny_name;
    @Enumerated(EnumType.STRING)
    private AeroLine aeroLine;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            mappedBy="fly"
    )
    private Set<TicketEntity> tickets;
}
