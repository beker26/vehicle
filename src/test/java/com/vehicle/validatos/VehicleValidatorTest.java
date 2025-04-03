package com.vehicle.validatos;

import com.vehicle.exceptions.BadRequestException;
import com.vehicle.exceptions.Issue;
import com.vehicle.exceptions.IssueEnum;
import com.vehicle.validators.VehicleValidator;
import org.junit.jupiter.api.Test;

import static com.vehicle.mock.MockedValues.YEAR_1999;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleValidatorTest {


    @Test
    void shouldGiveExceptionWhenYearIsLessThan2000() {
        final BadRequestException badRequestException =
                assertThrows(
                        BadRequestException.class, () -> VehicleValidator.validateIfYearOfManufactureIsLess(YEAR_1999));
        assertEquals(
                new Issue(IssueEnum.YEAR_OF_MANUFACTURE_IS_LESS, YEAR_1999).getMessage(),
                badRequestException.getMessage());
    }

}
