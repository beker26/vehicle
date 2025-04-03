package com.vehicle.controllers;

import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;


    public VehicleController(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @Operation(summary = "Creates a new vehicle", description = "Creates a new vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New vehicle created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid year, must be 2000 or greater.")
    })
    @PostMapping
    public ResponseEntity<VehiclePostResponse> createNewVehicle(@Validated @RequestBody final VehiclePostRequest vehiclePostRequest) {
        return new ResponseEntity<>(vehicleService.createNewVehicle(vehiclePostRequest), HttpStatus.CREATED);
    }
}
