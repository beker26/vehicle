package com.vehicle.validators;

import com.vehicle.domains.Vehicle;
import com.vehicle.exceptions.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;

public class VehicleValidator {

    private VehicleValidator() {}

    public static void  validateIfTheVehicleExistsInTheDatabase(final Optional<Vehicle> vehicle){
        if (vehicle.isEmpty()) throw NotFoundException.vehicleDoesNotExistInTheDatabase();
    }
}
