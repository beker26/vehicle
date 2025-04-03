package com.vehicle.converters;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;

public class VehicleConverter {

    private VehicleConverter() {
    }


    public static Vehicle fromVehiclePostRequestToVehicle(
            final VehiclePostRequest vehiclePostRequest) {

        Vehicle vehicleBuilder = Vehicle.builder()
                .vehicle(vehiclePostRequest.getVehicle())
                .brand(vehiclePostRequest.getBrand())
                .year(vehiclePostRequest.getYear())
                .description(vehiclePostRequest.getDescription())
                .sold(vehiclePostRequest.getSold())
                .build();

        return vehicleBuilder;
    }


    public static VehiclePostResponse fromVehiclePostResponseToVehicle(
            final Vehicle vehicle) {

        VehiclePostResponse vehicleResponseBuilder = VehiclePostResponse.builder()
                .vehicle(vehicle.getVehicle())
                .brand(vehicle.getBrand())
                .year(vehicle.getYear())
                .description(vehicle.getDescription())
                .sold(vehicle.getSold())
                .build();

        return vehicleResponseBuilder;
    }
}
