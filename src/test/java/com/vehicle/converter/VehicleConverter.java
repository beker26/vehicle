package com.vehicle.converter;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;
import org.junit.jupiter.api.Test;

import static com.vehicle.converters.VehicleConverter.fromVehiclePostRequestToVehicle;
import static com.vehicle.converters.VehicleConverter.fromVehiclePostResponseToVehicle;
import static com.vehicle.mock.VehicleMock.getVehicleMock;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VehicleConverter {


    @Test
    void shouldConverterVehiclePostResponseToVehicle() {

        VehiclePostResponse vehiclePostResponse = fromVehiclePostResponseToVehicle(getVehicleMock());

        assertNotNull(vehiclePostResponse);
    }

    @Test
    void shouldConverterVehiclePostRequestToVehicle() {

        Vehicle vehicle = fromVehiclePostRequestToVehicle(getVehiclePostRequest());

        assertNotNull(vehicle);
    }
}
