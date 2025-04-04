package com.vehicle.services;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehicleCountResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import com.vehicle.exceptions.NotFoundException;
import com.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.vehicle.exceptions.IssueEnum.VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE;
import static com.vehicle.exceptions.IssueEnum.VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE;
import static com.vehicle.mock.MockedValues.FALSE;
import static com.vehicle.mock.MockedValues.ID;
import static com.vehicle.mock.MockedValues.ID_NOT_EXIST;
import static com.vehicle.mock.MockedValues.THREE;
import static com.vehicle.mock.MockedValues.YEAR_2007;
import static com.vehicle.mock.MockedValues.ZERO;
import static com.vehicle.mock.VehicleMock.getVehicleMock;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePutRequestMock.getVehiclePutRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

        when(vehicleRepository.save(vehicleMock)).thenReturn(vehicleMock);

        final VehiclePostResponse newVehicleResponse = vehicleService.createNewVehicle(vehiclePostRequest);

        assertNotNull(newVehicleResponse);
        assertEquals(vehicleMock.getVehicle(), newVehicleResponse.getVehicle());
        assertEquals(vehicleMock.getYear(), newVehicleResponse.getYear());
        assertEquals(vehicleMock.getBrand(), newVehicleResponse.getBrand());
        assertEquals(vehicleMock.getSold(), newVehicleResponse.getSold());
        assertEquals(vehicleMock.getDescription(), newVehicleResponse.getDescription());
    }

    @Test
    void shouldUpdateAVehicleSuccessfully() {

        final VehiclePutRequest vehiclePutRequest = getVehiclePutRequest();

        final Vehicle vehicleMock = getVehicleMock();

        when(vehicleRepository.findById(eq(ID))).thenReturn(Optional.ofNullable(vehicleMock));
        when(vehicleRepository.save(vehicleMock)).thenReturn(vehicleMock);

        final VehiclePutResponse vehiclePutResponse = vehicleService.updateVehicle(ID, vehiclePutRequest);

        assertNotNull(vehiclePutResponse);
        assertEquals(vehicleMock.getVehicle(), vehiclePutResponse.getVehicle());
        assertEquals(vehicleMock.getYear(), vehiclePutResponse.getYear());
        assertEquals(vehicleMock.getBrand(), vehiclePutResponse.getBrand());
        assertEquals(vehicleMock.getSold(), vehiclePutResponse.getSold());
        assertEquals(vehicleMock.getDescription(), vehiclePutResponse.getDescription());
    }


    @Test
    void shouldDeleteAVehicleSuccessfully() {
        final Vehicle vehicleMock = getVehicleMock();

        when(vehicleRepository.findById(eq(ID))).thenReturn(Optional.of(vehicleMock));
        doNothing().when(vehicleRepository).delete(vehicleMock);

        vehicleService.deleteVehicle(ID);

        verify(vehicleRepository, times(1)).findById(eq(ID));
        verify(vehicleRepository, times(1)).delete(vehicleMock);
    }


    @Test
    void shouldDeleteAVehicleNotFound() {

        when(vehicleRepository.findById(eq(ID))).thenReturn(Optional.empty());

        final NotFoundException exception =
                assertThrows(
                        NotFoundException.class,
                        () -> {
                            vehicleService.deleteVehicle(ID);
                            ;
                        });


        verify(vehicleRepository, times(1)).findById(eq(ID));
        assertEquals(
                VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE.getFormattedMessage(),
                exception.getIssue().getMessage());
    }

    @Test
    void shouldUpdateAVehicleNotFound() {

        final VehiclePutRequest vehiclePutRequest = getVehiclePutRequest();

        when(vehicleRepository.findById(eq(ID_NOT_EXIST))).thenReturn(Optional.empty());

        final NotFoundException exception =
                assertThrows(
                        NotFoundException.class,
                        () -> {
                            vehicleService.updateVehicle(ID_NOT_EXIST, vehiclePutRequest);
                        });

        assertEquals(
                VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE.getFormattedMessage(),
                exception.getIssue().getMessage());
    }

    @Test
    void shouldCountVehiclesNotSaleSuccessfully() {

        when(vehicleRepository.countVehiclesNotSold(FALSE)).thenReturn(THREE);

        VehicleCountResponse countVehiclesNotSold = vehicleService.getCountVehiclesNotSold();

        assertNotNull(countVehiclesNotSold);
        assertEquals(THREE, countVehiclesNotSold.getVehicleNumbers());
    }

    @Test
    void shouldCountVehiclesNotSaleNotFound() {

        when(vehicleRepository.countVehiclesNotSold(FALSE)).thenReturn(ZERO);

        final NotFoundException exception =
                assertThrows(
                        NotFoundException.class,
                        () -> {
                            vehicleService.getCountVehiclesNotSold();
                        });

        assertEquals(
                VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE.getFormattedMessage(),
                exception.getIssue().getMessage());
    }

    @Test
    void shouldCountVehiclesByYearOfManufactureSuccessfully() {

        when(vehicleRepository.countVehiclesForYear(YEAR_2007)).thenReturn(THREE);

        VehicleCountResponse countVehiclesForYear = vehicleService.getVehiclesByYearOfManufacture(YEAR_2007);

        assertNotNull(countVehiclesForYear);
        assertEquals(THREE, countVehiclesForYear.getVehicleNumbers());
    }

    @Test
    void shouldCountVehiclesByYearOfManufactureNotFound() {

        when(vehicleRepository.countVehiclesForYear(YEAR_2007)).thenReturn(ZERO);

        final NotFoundException exception =
                assertThrows(
                        NotFoundException.class,
                        () -> {
                            vehicleService.getVehiclesByYearOfManufacture(YEAR_2007);
                        });

        assertEquals(
                VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE.getFormattedMessage(),
                exception.getIssue().getMessage());
    }
}
