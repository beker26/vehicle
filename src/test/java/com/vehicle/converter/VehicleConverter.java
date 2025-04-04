package com.vehicle.converter;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;
import org.junit.jupiter.api.Test;

import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePutResponseToVehicle;
import static com.vehicle.mock.MockedValues.ID;
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
}
