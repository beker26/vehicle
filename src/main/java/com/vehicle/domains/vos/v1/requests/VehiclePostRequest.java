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

import static com.vehicle.constants.VehicleRequestConstants.DESCRIPTION;
import static com.vehicle.constants.VehicleRequestConstants.DESCRIPTION_BRAND;
import static com.vehicle.constants.VehicleRequestConstants.DESCRIPTION_SOLD;
import static com.vehicle.constants.VehicleRequestConstants.DESCRIPTION_VEHICLE;
import static com.vehicle.constants.VehicleRequestConstants.DESCRIPTION_YEAR;
import static com.vehicle.constants.VehicleRequestConstants.EXAMPLE_BRAND;
import static com.vehicle.constants.VehicleRequestConstants.EXAMPLE_DESCRIPTION;
import static com.vehicle.constants.VehicleRequestConstants.EXAMPLE_SOLD;
import static com.vehicle.constants.VehicleRequestConstants.EXAMPLE_VEHICLE;
import static com.vehicle.constants.VehicleRequestConstants.EXAMPLE_YEAR;
import static com.vehicle.constants.VehicleRequestConstants.TRUE;

@Builder
@Getter
@Setter
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VehiclePostRequest {

    @Schema(required = TRUE, description = DESCRIPTION_VEHICLE, example = EXAMPLE_VEHICLE)
    @Size(min = 1, max = 50)
    @NotBlank
    private String vehicle;

    @Schema(required = TRUE, description = DESCRIPTION_BRAND, example = EXAMPLE_BRAND)
    @Size(min = 1, max = 50)
    @NotBlank
    private String brand;

    @Schema(required = TRUE, description = DESCRIPTION_YEAR, example = EXAMPLE_YEAR)
    @NotNull
    private Integer year;

    @Schema(required = TRUE, description = DESCRIPTION, example = EXAMPLE_DESCRIPTION)
    @Size(min = 1, max = 1000)
    @NotBlank
    private String description;

    @Schema(required = TRUE, description = DESCRIPTION_SOLD, example = EXAMPLE_SOLD)
    @NotNull
    private Boolean sold;
}
