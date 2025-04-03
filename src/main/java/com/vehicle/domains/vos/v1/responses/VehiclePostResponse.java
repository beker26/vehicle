package com.vehicle.domains.vos.v1.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePostResponse {

    private String vehicle;

    private String brand;

    private Integer year;

    private String description;

    private Boolean sold;
}
