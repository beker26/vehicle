package com.vehicle.mock;

import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;

import static com.vehicle.mock.MockedValues.BRAND_ONE;
import static com.vehicle.mock.MockedValues.DESCRIPTION;
import static com.vehicle.mock.MockedValues.SOLD;
import static com.vehicle.mock.MockedValues.VEHICLE_ONE;
import static com.vehicle.mock.MockedValues.YEAR_1999;
import static com.vehicle.mock.MockedValues.YEAR_2007;

public class VehiclePostRequestMock {

    public static VehiclePostRequest getVehiclePostRequest() {
        return VehiclePostRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_ONE)
                .year(YEAR_2007)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }

    public static VehiclePostRequest getVehiclePostExceptionYearRequest() {
        return VehiclePostRequest.builder()
                .vehicle(VEHICLE_ONE)
                .brand(BRAND_ONE)
                .year(YEAR_1999)
                .description(DESCRIPTION)
                .sold(SOLD)
                .build();
    }


}
