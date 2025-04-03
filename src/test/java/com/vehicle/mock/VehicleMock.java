package com.vehicle.mock;

import com.vehicle.domains.Vehicle;
import com.vehicle.domains.vos.v1.responses.VehiclePostResponse;

import static com.vehicle.mock.MockedValues.BRAND_ONE;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;

public class VehicleMock {

    public static Vehicle getVehicleMock() {
        return Vehicle.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_ONE)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
