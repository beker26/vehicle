package com.vehicle.mock;

import com.vehicle.domains.vos.v1.responses.VehiclePutResponse;

import static com.vehicle.mock.MockedValues.BRAND_ONE;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;

public class VehiclePutResponseMock {

    public static VehiclePutResponse getVehiclePutResponse() {
        return VehiclePutResponse.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_ONE)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
