package com.vehicle.mock;

import com.vehicle.domains.vos.v1.responses.VehicleGetResponse;

import static com.vehicle.mock.MockedValues.BRAND_TOYOTA;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_2007;

public class VehicleGetResponseMock {


    public static VehicleGetResponse getVehicleGetResponse() {
        return VehicleGetResponse.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_TOYOTA)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }
}
