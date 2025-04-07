package com.vehicle.validators;

import com.vehicle.domains.Vehicle;
import com.vehicle.exceptions.BadRequestException;
import com.vehicle.exceptions.NotFoundException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static com.vehicle.constants.ValidationConstants.FOUR;
import static com.vehicle.constants.ValidationConstants.ZERO;

public class VehicleValidator {

    private VehicleValidator() {
    }

    public static void validateIfTheVehicleExistsInTheDatabase(final Optional<Vehicle> vehicle) {
        if (vehicle.isEmpty()) throw NotFoundException.vehicleDoesNotExistInTheDatabase();
    }

    public static void validateIfThereAreActiveVehiclesInTheBase(final Integer countVehicles) {
        if (countVehicles.compareTo(ZERO) == ZERO) throw NotFoundException.thereAreNoActiveVehiclesInTheBase();
    }

    public static void validatesIfTheYearHasFourNumbers(final Integer year) {
        if (String.valueOf(year).length() != FOUR) throw BadRequestException.theNumberOfTheYearIsDifferentFromFour();
    }

    public static void validateIfTheListVehicleExistsInTheDatabase(final List<Vehicle> vehicles) {
        if (ObjectUtils.isEmpty(vehicles)) throw NotFoundException.thereAreNoActiveVehiclesInTheBase();
    }
}
