package com.vehicle.services;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehicleCountResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import com.vehicle.repositories.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vehicle.constants.ServiceConstants.FALSE;
import static com.vehicle.converters.VehicleConverter.fromCountVehicleGetResponseToCountVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutResponseToVehicle;
import static com.vehicle.validators.VehicleValidator.validateIfTheVehicleExistsInTheDatabase;
import static com.vehicle.validators.VehicleValidator.validateIfThereAreActiveVehiclesInTheBase;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public VehiclePostResponse createNewVehicle(final VehiclePostRequest vehiclePostRequest) {

        final Vehicle vehicle = fromVehiclePostRequestToVehicle(vehiclePostRequest);

        final Vehicle savedVehicle = vehicleRepository.save(vehicle);

        final VehiclePostResponse vehiclePostResponse = fromVehiclePostResponseToVehicle(savedVehicle);

        return vehiclePostResponse;
    }

    @Transactional
    public VehiclePutResponse updateVehicle(final Long id, final VehiclePutRequest vehiclePutRequest) {

        final Optional<Vehicle> findVehicle = vehicleRepository.findById(id);

        validateIfTheVehicleExistsInTheDatabase(findVehicle);

        final Vehicle vehicle = fromVehiclePutRequestToVehicle(id, vehiclePutRequest);

        final Vehicle savedVehicle = vehicleRepository.save(vehicle);

        final VehiclePutResponse vehiclePutResponse = fromVehiclePutResponseToVehicle(savedVehicle);

        return vehiclePutResponse;
    }

    @Transactional
    public void deleteVehicle(final Long id) {

        final Optional<Vehicle> findVehicle = vehicleRepository.findById(id);

        validateIfTheVehicleExistsInTheDatabase(findVehicle);

        vehicleRepository.delete(findVehicle.get());
    }

    public VehicleCountResponse getCountVehiclesNotSold() {

        final Integer countVehicles = vehicleRepository.countVehiclesNotSold(FALSE);

        validateIfThereAreActiveVehiclesInTheBase(countVehicles);

        final VehicleCountResponse vehicleCountResponse = fromCountVehicleGetResponseToCountVehicle(countVehicles);

        return vehicleCountResponse;
    }
}
