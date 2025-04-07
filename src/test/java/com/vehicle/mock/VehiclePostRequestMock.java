package com.vehicle.mock;

import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;

import static com.vehicle.mock.MockedValues.BRAND_TOYOTA;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;
import static com.vehicle.mock.MockedValues.YEAR_DIFFERENT;

public class VehiclePostRequestMock {

    public static VehiclePostRequest getVehiclePostRequest() {
        return VehiclePostRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }

    public static VehiclePostRequest getVehiclePostYearDifferentThanFourRequest() {
        return VehiclePostRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_DIFFERENT)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
