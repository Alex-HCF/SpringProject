package org.example.data.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationOutDto {
    private UUID fiasId;
    private String address;
    private Double longitude;
    private Double latitude;
}
