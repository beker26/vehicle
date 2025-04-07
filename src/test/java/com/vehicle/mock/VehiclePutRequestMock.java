package com.vehicle.mock;

import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;

import static com.vehicle.mock.MockedValues.BRAND_TOYOTA;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;
import static com.vehicle.mock.MockedValues.YEAR_DIFFERENT;

public class VehiclePutRequestMock {

    public static VehiclePutRequest getVehiclePutRequest() {
        return VehiclePutRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }

    public static VehiclePutRequest getVehiclePutYearDifferentThanFourRequest() {
        return VehiclePutRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_DIFFERENT)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
