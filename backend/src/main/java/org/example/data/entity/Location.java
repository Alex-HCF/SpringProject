package org.example.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_fias_id")
    private UUID regionFiasId;

    @Column(name = "area_fias_id")
    private UUID areaFiasId;

    @Column(name = "city_fias_id")
    private UUID cityFiasId;

    @Column(name = "settlement_fias_id")
    private UUID settlementFiasId;

    @Column(name = "street_fias_id")
    private UUID streetFiasId;

    @Column(name = "house_fias_id")
    private UUID houseFiasId;

    private Double longitude;

    private Double latitude;
}
