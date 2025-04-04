package com.vehicle.domains.vos.v1.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VehiclePutRequest {

    @Schema(required = true, description = "Vehicle Name", example = "Hatch Car")
    @Size(min = 1, max = 50)
    @NotBlank
    private String vehicle;

    @Schema(required = true, description = "Vehicle brand", example = "Toyota")
    @Size(min = 1, max = 50)
    @NotBlank
    private String brand;

    @Schema(required = true, description = "Vehicle manufacture year", example = "2007")
    @NotNull
    private Integer year;

    @Schema(required = true, description = "Vehicle description", example = "Reliable car with good fuel efficiency")
    @Size(min = 1, max = 1000)
    @NotBlank
    private String description;

    @Schema(required = true, description = "Tag to check if the vehicle has been sold", example = "false")
    @NotNull
    private Boolean sold;
}
