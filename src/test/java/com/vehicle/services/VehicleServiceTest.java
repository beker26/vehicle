package com.vehicle.services;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.exceptions.BadRequestException;
import com.vehicle.mock.VehiclePostResponseMock;
import com.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.vehicle.exceptions.IssueEnum.YEAR_OF_MANUFACTURE_IS_LESS;
import static com.vehicle.mock.MockedValues.YEAR_1999;
import static com.vehicle.mock.VehicleMock.getVehicleMock;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostExceptionYearRequest;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePostResponseMock.getVehiclePostResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;


    @Test
    void shouldCreateANewVehicleSuccessfully() {

        final VehiclePostRequest vehiclePostRequest = getVehiclePostRequest();

        final Vehicle vehicleMock = getVehicleMock();

        when(vehicleRepository.save(any())).thenReturn(vehicleMock);

        final VehiclePostResponse newVehicleResponse = vehicleService.createNewVehicle(vehiclePostRequest);

        assertNotNull(newVehicleResponse);
        assertEquals(vehicleMock.getVehicle(), newVehicleResponse.getVehicle());
        assertEquals(vehicleMock.getYear(), newVehicleResponse.getYear());
        assertEquals(vehicleMock.getBrand(), newVehicleResponse.getBrand());
        assertEquals(vehicleMock.getSold(), newVehicleResponse.getSold());
        assertEquals(vehicleMock.getDescription(), newVehicleResponse.getDescription());
    }

    @Test
    void shouldGiveExceptionWhenYearIsLessThan2000() {

        final VehiclePostRequest vehiclePostRequest = getVehiclePostExceptionYearRequest();

        final BadRequestException exception =
                assertThrows(
                        BadRequestException.class,
                        () ->
                                vehicleService.createNewVehicle(vehiclePostRequest));

        assertEquals(
                String.format(YEAR_OF_MANUFACTURE_IS_LESS.getMessage(), YEAR_1999),
                exception.getIssue().getMessage()
        );
    }
}
