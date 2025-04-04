package com.vehicle.mock;

import com.vehicle.domains.Vehicle;

import static com.vehicle.mock.MockedValues.BRAND_TOYOTA;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.ID;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;

public class VehicleMock {

    public static Vehicle getVehicleMock() {
        return Vehicle.builder()
                .id(ID)
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
