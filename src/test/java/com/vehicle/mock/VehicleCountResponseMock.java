package com.vehicle.mock;

import com.vehicle.domains.vos.v1.responses.VehicleCountResponse;

import static com.vehicle.mock.MockedValues.THREE;

public class VehicleCountResponseMock {

    public static VehicleCountResponse getVehicleCountResponseMock() {
        return VehicleCountResponse.builder()
                .vehicleNumbers(THREE)
                .build();
    }
}
