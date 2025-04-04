package com.vehicle.validatos;

import com.vehicle.exceptions.Issue;
import com.vehicle.exceptions.IssueEnum;
import com.vehicle.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.vehicle.validators.VehicleValidator.validateIfTheVehicleExistsInTheDatabase;
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


}
