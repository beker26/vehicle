package com.vehicle.validators;

import com.vehicle.exceptions.BadRequestException;

public class VehicleValidator {

    private VehicleValidator() {}

    public static void validateIfYearOfManufactureIsLess(final Integer year) {
        if (year.compareTo(2000) < 0) throw BadRequestException.yearOfManufactureIsLess(year);
    }
}
