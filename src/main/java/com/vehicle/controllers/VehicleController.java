package com.vehicle.controllers;

import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import com.vehicle.services.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
            @ApiResponse(responseCode = "201", description = "New vehicle created successfully.")
    })
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiclePostResponse> createNewVehicle(@Validated @RequestBody final VehiclePostRequest vehiclePostRequest) {
        return new ResponseEntity<>(vehicleService.createNewVehicle(vehiclePostRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a vehicle", description = "Update a vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehiclePutResponse> updateVehicle(@PathVariable final Long id,  @Validated @RequestBody final VehiclePutRequest vehiclePutRequest) {
        return new ResponseEntity<>(vehicleService.updateVehicle(id, vehiclePutRequest), HttpStatus.OK);
    }

    @Operation(summary = "Delete a vehicle", description = "Delete a vehicle in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicle Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable final Long id) {
        vehicleService.deleteVehicle(id);
    }
}
