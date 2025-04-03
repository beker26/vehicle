package com.vehicle.services;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.validators.VehicleValidator.validateIfYearOfManufactureIsLess;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public VehiclePostResponse createNewVehicle(final VehiclePostRequest vehiclePostRequest) {

        validateIfYearOfManufactureIsLess(vehiclePostRequest.getYear());

        Vehicle vehicle = fromVehiclePostRequestToVehicle(vehiclePostRequest);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        VehiclePostResponse vehiclePostResponse = fromVehiclePostResponseToVehicle(savedVehicle);

        return vehiclePostResponse;
    }
}
