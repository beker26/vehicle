package com.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.controllers.VehicleController;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.exceptions.GlobalExceptionHandler;
import com.vehicle.exceptions.NotFoundException;
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

import static com.vehicle.mock.MockedValues.ID;
import static com.vehicle.mock.MockedValues.ID_NOT_EXIST;
import static com.vehicle.mock.MockedValues.SLASH;
import static com.vehicle.mock.MockedValues.THREE;
import static com.vehicle.mock.MockedValues.URL_VEHICLES;
import static com.vehicle.mock.MockedValues.VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePostResponseMock.getVehiclePostResponse;
import static com.vehicle.mock.VehiclePutRequestMock.getVehiclePutRequest;
import static com.vehicle.mock.VehiclePutResponseMock.getVehiclePutResponse;
import static net.bytebuddy.implementation.bytecode.constant.IntegerConstant.FOUR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        final VehiclePostRequest request = getVehiclePostRequest();

        Mockito.when(vehicleService.createNewVehicle(any(VehiclePostRequest.class)))
                .thenReturn(getVehiclePostResponse());

        mockMvc.perform(post(URL_VEHICLES)
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
    void shouldUpdateAVehicleSuccessfully() throws Exception {
        final VehiclePutRequest request = getVehiclePutRequest();

        Mockito.when(vehicleService.updateVehicle(eq(ID), any(VehiclePutRequest.class)))
                .thenReturn(getVehiclePutResponse());

        mockMvc.perform(put(URL_VEHICLES + SLASH + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vehicle").value(request.getVehicle()))
                .andExpect(jsonPath("$.brand").value(request.getBrand()))
                .andExpect(jsonPath("$.year").value(request.getYear()))
                .andExpect(jsonPath("$.description").value(request.getDescription()))
                .andExpect(jsonPath("$.sold").value(request.getSold()));
    }

    @Test
    void shouldUpdateAVehicleNotFoundException() throws Exception {
        final VehiclePutRequest request = getVehiclePutRequest();

        Mockito.when(vehicleService.updateVehicle(eq(ID_NOT_EXIST), any(VehiclePutRequest.class)))
                .thenThrow(NotFoundException.vehicleDoesNotExistInTheDatabase());

        mockMvc.perform(put(URL_VEHICLES + SLASH + ID_NOT_EXIST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(THREE))
                .andExpect(jsonPath("$.message").value(VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }
}
