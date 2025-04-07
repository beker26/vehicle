package com.vehicle.validatos;

import com.vehicle.exceptions.BadRequestException;
import com.vehicle.exceptions.Issue;
import com.vehicle.exceptions.IssueEnum;
import com.vehicle.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.vehicle.mock.MockedValues.THREE;
import static com.vehicle.mock.MockedValues.ZERO;
import static com.vehicle.validators.VehicleValidator.validateIfTheListVehicleExistsInTheDatabase;
import static com.vehicle.validators.VehicleValidator.validateIfTheVehicleExistsInTheDatabase;
import static com.vehicle.validators.VehicleValidator.validateIfThereAreActiveVehiclesInTheBase;
import static com.vehicle.validators.VehicleValidator.validatesIfTheYearHasFourNumbers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleValidatorTest {


    @Test
    void shouldGiveExceptionWhenTheVehicleDoesNotExistInTheBase() {

        final NotFoundException notFoundException =
                assertThrows(
                        NotFoundException.class, () ->  validateIfTheVehicleExistsInTheDatabase(Optional.empty()));
        assertEquals(
                new Issue(IssueEnum.VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE).getMessage(),
                notFoundException.getMessage());
    }

    @Test
    void shouldGiveExceptionWhenThereAreActiveVehiclesInTheBase() {

        final NotFoundException notFoundException =
                assertThrows(
                        NotFoundException.class, () ->  validateIfThereAreActiveVehiclesInTheBase(ZERO));
        assertEquals(
                new Issue(IssueEnum.VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE).getMessage(),
                notFoundException.getMessage());
    }

    @Test
    void shouldIfTheListVehicleExistsInTheDatabase() {

        final NotFoundException notFoundException =
                assertThrows(
                        NotFoundException.class, () ->  validateIfTheListVehicleExistsInTheDatabase(List.of()));
        assertEquals(
                new Issue(IssueEnum.VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE).getMessage(),
                notFoundException.getMessage());
    }


    @Test
    void shouldValidatesIfTheYearHasFourNumbers() {

        final BadRequestException notFoundException =
                assertThrows(
                        BadRequestException.class, () ->  validatesIfTheYearHasFourNumbers(THREE));
        assertEquals(
                new Issue(IssueEnum.THE_NUMBER_OF_THE_YEAR_IS_DIFFERENT_FROM_FOUR).getMessage(),
                notFoundException.getMessage());
    }


}
