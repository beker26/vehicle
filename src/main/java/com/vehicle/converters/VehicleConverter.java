package com.vehicle.converters;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehicleCountResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;

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

    public static Vehicle fromVehiclePutRequestToVehicle(final Long id, final VehiclePutRequest vehiclePutRequest){

        Vehicle vehicleBuilder = Vehicle.builder()
                .id(id)
                .vehicle(vehiclePutRequest.getVehicle())
                .brand(vehiclePutRequest.getBrand())
                .year(vehiclePutRequest.getYear())
                .description(vehiclePutRequest.getDescription())
                .sold(vehiclePutRequest.getSold())
                .build();

        return vehicleBuilder;
    }

    public static VehicleCountResponse fromCountVehicleGetResponseToCountVehicle(final Integer countVehicles){
        return VehicleCountResponse.builder()
                .vehicleNumbers(countVehicles)
                .build();
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

    public static VehiclePutResponse fromVehiclePutResponseToVehicle(
            final Vehicle vehicle) {

        VehiclePutResponse vehicleResponseBuilder = VehiclePutResponse.builder()
                .vehicle(vehicle.getVehicle())
                .brand(vehicle.getBrand())
                .year(vehicle.getYear())
                .description(vehicle.getDescription())
                .sold(vehicle.getSold())
                .build();

        return vehicleResponseBuilder;
    }
}
