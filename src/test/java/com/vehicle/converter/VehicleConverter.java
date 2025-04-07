package com.vehicle.converter;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.responses.VehicleCountResponse;
import com.vehicle.domains.vos.v1.responses.VehicleGetResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.vehicle.converters.VehicleConverter.fromCountVehicleGetResponseToCountVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehicleGetResponseToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutResponseToVehicle;
import static com.vehicle.mock.MockedValues.ID;
import static com.vehicle.mock.MockedValues.ONE;
import static com.vehicle.mock.MockedValues.ONE_LONG;
import static com.vehicle.mock.MockedValues.THREE;
import static com.vehicle.mock.MockedValues.ZERO;
import static com.vehicle.mock.VehicleMock.getVehicleMock;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePutRequestMock.getVehiclePutRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VehicleConverter {


    @Test
    void shouldConverterVehiclePostResponseToVehicle() {

        final VehiclePostResponse vehiclePostResponse = fromVehiclePostResponseToVehicle(getVehicleMock());

        assertNotNull(vehiclePostResponse);
    }

    @Test
    void shouldConverterVehiclePutResponseToVehicle() {

        final VehiclePutResponse vehiclePutResponse = fromVehiclePutResponseToVehicle(getVehicleMock());

        assertNotNull(vehiclePutResponse);
    }

    @Test
    void shouldConverterVehiclePostRequestToVehicle() {

        final Vehicle vehicle = fromVehiclePostRequestToVehicle(getVehiclePostRequest());

        assertNotNull(vehicle);
    }

    @Test
    void shouldConverterVehiclePutRequestToVehicle() {

        final Vehicle vehicle = fromVehiclePutRequestToVehicle(ID, getVehiclePutRequest());

        assertNotNull(vehicle);
    }

    @Test
    void shouldConverterCountVehicleGetResponseToCountVehicle() {

        final VehicleCountResponse countVehicle = fromCountVehicleGetResponseToCountVehicle(THREE);

        assertNotNull(countVehicle);
    }

    @Test
    void shouldConverterVehicleGetResponseToVehicle() {

        final Pageable pageable = PageRequest.of(ZERO, ONE);

        final Page<Vehicle> page = new PageImpl<>(List.of(getVehicleMock()), pageable, ONE_LONG);

        final Page<VehicleGetResponse> vehicleGetResponse = fromVehicleGetResponseToVehicle(page);

        assertNotNull(vehicleGetResponse);
    }
}
