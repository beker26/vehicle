package com.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.controllers.VehicleController;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.exceptions.BadRequestException;
import com.vehicle.exceptions.GlobalExceptionHandler;
import com.vehicle.services.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.vehicle.mock.MockedValues.ONE;
import static com.vehicle.mock.MockedValues.URL_VEHICLES;
import static com.vehicle.mock.MockedValues.YEAR_OF_MANUFACTURE_IS_LESS_EXCEPTION;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostExceptionYearRequest;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePostResponseMock.getVehiclePostResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }
    @Test
    void shouldCreateANewVehicleSuccessfully() throws Exception {
        VehiclePostRequest request = getVehiclePostRequest();

        Mockito.when(vehicleService.createNewVehicle(Mockito.any(VehiclePostRequest.class)))
                .thenReturn(getVehiclePostResponse());

        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vehicle").value(request.getVehicle()))
                .andExpect(jsonPath("$.brand").value(request.getBrand()))
                .andExpect(jsonPath("$.year").value(request.getYear()))
                .andExpect(jsonPath("$.description").value(request.getDescription()))
                .andExpect(jsonPath("$.sold").value(request.getSold()));
    }

    @Test
    void shouldGiveExceptionWhenYearIsLessThan2000() throws Exception {
        VehiclePostRequest request = getVehiclePostExceptionYearRequest() ;

        Mockito.doThrow(BadRequestException.yearOfManufactureIsLess(request.getYear()))
                .when(vehicleService).createNewVehicle(Mockito.any(VehiclePostRequest.class));

        mockMvc.perform(post(URL_VEHICLES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ONE))
                .andExpect(jsonPath("$.message").value(YEAR_OF_MANUFACTURE_IS_LESS_EXCEPTION));
    }


}
