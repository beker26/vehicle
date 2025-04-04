package com.vehicle.services;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import com.vehicle.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutResponseToVehicle;
import static com.vehicle.validators.VehicleValidator.validateIfTheVehicleExistsInTheDatabase;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    public VehiclePostResponse createNewVehicle(final VehiclePostRequest vehiclePostRequest) {

        final Vehicle vehicle = fromVehiclePostRequestToVehicle(vehiclePostRequest);

        final Vehicle savedVehicle = vehicleRepository.save(vehicle);

        final VehiclePostResponse vehiclePostResponse = fromVehiclePostResponseToVehicle(savedVehicle);

        return vehiclePostResponse;
    }

    public VehiclePutResponse updateVehicle(final Long id, final VehiclePutRequest vehiclePutRequest) {

        final Optional<Vehicle> findVehicle = vehicleRepository.findById(id);

        validateIfTheVehicleExistsInTheDatabase(findVehicle);

        final Vehicle vehicle = fromVehiclePutRequestToVehicle(id, vehiclePutRequest);

        final Vehicle savedVehicle = vehicleRepository.save(vehicle);

        final VehiclePutResponse vehiclePutResponse = fromVehiclePutResponseToVehicle(savedVehicle);

        return vehiclePutResponse;
    }
}
