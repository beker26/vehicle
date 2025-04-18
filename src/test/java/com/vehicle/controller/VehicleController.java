package com.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.controllers.VehicleController;
import com.vehicle.domains.vos.v1.requests.VehiclePostRequest;
import com.vehicle.domains.vos.v1.requests.VehiclePutRequest;
import com.vehicle.domains.vos.v1.responses.VehicleGetResponse;
import com.vehicle.exceptions.BadRequestException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.vehicle.mock.MockedValues.BRAND_BYD;
import static com.vehicle.mock.MockedValues.BRAND_TOYOTA;
import static com.vehicle.mock.MockedValues.FIVE;
import static com.vehicle.mock.MockedValues.FOUR;
import static com.vehicle.mock.MockedValues.ID;
import static com.vehicle.mock.MockedValues.ID_NOT_EXIST;
import static com.vehicle.mock.MockedValues.ONE;
import static com.vehicle.mock.MockedValues.ONE_LONG;
import static com.vehicle.mock.MockedValues.ONE_STRING;
import static com.vehicle.mock.MockedValues.SLASH;
import static com.vehicle.mock.MockedValues.THE_NUMBER_OF_THE_YEAR_IS_DIFFERENT_FROM_FOUR;
import static com.vehicle.mock.MockedValues.THREE;
import static com.vehicle.mock.MockedValues.URL_CAR_REGISTRATION_WEEK;
import static com.vehicle.mock.MockedValues.URL_COUNT_BRAND;
import static com.vehicle.mock.MockedValues.URL_COUNT_NOT_SOLD;
import static com.vehicle.mock.MockedValues.URL_COUNT_YEAR;
import static com.vehicle.mock.MockedValues.URL_VEHICLES;
import static com.vehicle.mock.MockedValues.VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE;
import static com.vehicle.mock.MockedValues.VEHICLE_CAR;
import static com.vehicle.mock.MockedValues.VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE;
import static com.vehicle.mock.MockedValues.YEAR_1999;
import static com.vehicle.mock.MockedValues.YEAR_2007;
import static com.vehicle.mock.MockedValues.ZERO;
import static com.vehicle.mock.MockedValues.ZERO_STRING;
import static com.vehicle.mock.VehicleCountResponseMock.getVehicleCountResponseMock;
import static com.vehicle.mock.VehicleGetResponseMock.getVehicleGetResponse;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostRequest;
import static com.vehicle.mock.VehiclePostRequestMock.getVehiclePostYearDifferentThanFourRequest;
import static com.vehicle.mock.VehiclePostResponseMock.getVehiclePostResponse;
import static com.vehicle.mock.VehiclePutRequestMock.getVehiclePutRequest;
import static com.vehicle.mock.VehiclePutRequestMock.getVehiclePutYearDifferentThanFourRequest;
import static com.vehicle.mock.VehiclePutResponseMock.getVehiclePutResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
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
    void shouldReturnBadRequestOnCreateWhenYearIsDifferentThanFour() throws Exception {
        final VehiclePostRequest request = getVehiclePostYearDifferentThanFourRequest();

        Mockito.when(vehicleService.createNewVehicle(any(VehiclePostRequest.class)))
                .thenThrow(BadRequestException.theNumberOfTheYearIsDifferentFromFour());

        mockMvc.perform(post(URL_VEHICLES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(FIVE))
                .andExpect(jsonPath("$.message").value(THE_NUMBER_OF_THE_YEAR_IS_DIFFERENT_FROM_FOUR));
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
    void shouldDeleteAVehicleSuccessfully() throws Exception {
        Mockito.doNothing().when(vehicleService).deleteVehicle(eq(ID));

        mockMvc.perform(delete(URL_VEHICLES + SLASH + ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    void shouldDeleteAVehicleNotFoundException() throws Exception {

        Mockito.doThrow(NotFoundException.vehicleDoesNotExistInTheDatabase())
                .when(vehicleService).deleteVehicle(eq(ID_NOT_EXIST));

        mockMvc.perform(delete(URL_VEHICLES + SLASH + ID_NOT_EXIST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(THREE))
                .andExpect(jsonPath("$.message").value(VEHICLE_DOES_NOT_EXIST_IN_THE_DATA_BASE));
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

    @Test
    void shouldReturnBadRequestOnUpdateWhenYearIsDifferentThanFour() throws Exception {
        final VehiclePutRequest request = getVehiclePutYearDifferentThanFourRequest();

        Mockito.when(vehicleService.updateVehicle(eq(ID), any(VehiclePutRequest.class)))
                .thenThrow(BadRequestException.theNumberOfTheYearIsDifferentFromFour());

        mockMvc.perform(put(URL_VEHICLES + SLASH + ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code").value(FIVE))
                .andExpect(jsonPath("$.message").value(THE_NUMBER_OF_THE_YEAR_IS_DIFFERENT_FROM_FOUR));
    }

    @Test
    void shouldCountVehiclesNotSaleSuccessfully() throws Exception {

        Mockito.when(vehicleService.getCountVehiclesNotSold())
                .thenReturn(getVehicleCountResponseMock());

        mockMvc.perform(get(URL_VEHICLES + URL_COUNT_NOT_SOLD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vehicleNumbers").value(3));
    }

    @Test
    void shouldCountVehiclesNotSaleNotFound() throws Exception {

        Mockito.when(vehicleService.getCountVehiclesNotSold())
                .thenThrow(NotFoundException.thereAreNoActiveVehiclesInTheBase());


        mockMvc.perform(get(URL_VEHICLES + URL_COUNT_NOT_SOLD)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(FOUR))
                .andExpect(jsonPath("$.message").value(VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }

    @Test
    void shouldCountVehiclesByYearOfManufactureSuccessfully() throws Exception {

        Mockito.when(vehicleService.getVehiclesByYearOfManufacture(YEAR_2007))
                .thenReturn(getVehicleCountResponseMock());

        mockMvc.perform(get(URL_VEHICLES + SLASH + YEAR_2007 + URL_COUNT_YEAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vehicleNumbers").value(3));
    }

    @Test
    void shouldCountVehiclesByYearOfManufactureNotFound() throws Exception {

        Mockito.when(vehicleService.getVehiclesByYearOfManufacture(YEAR_1999))
                .thenThrow(NotFoundException.thereAreNoActiveVehiclesInTheBase());


        mockMvc.perform(get(URL_VEHICLES + SLASH + YEAR_1999 + URL_COUNT_YEAR)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(FOUR))
                .andExpect(jsonPath("$.message").value(VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }

    @Test
    void shouldCountVehiclesByBrandSuccessfully() throws Exception {

        Mockito.when(vehicleService.getVehiclesByBrand(BRAND_TOYOTA))
                .thenReturn(getVehicleCountResponseMock());

        mockMvc.perform(get(URL_VEHICLES + SLASH + BRAND_TOYOTA + URL_COUNT_BRAND)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.vehicleNumbers").value(3));
    }

    @Test
    void shouldCountVehiclesByBrandNotFound() throws Exception {

        Mockito.when(vehicleService.getVehiclesByBrand(BRAND_BYD))
                .thenThrow(NotFoundException.thereAreNoActiveVehiclesInTheBase());

        mockMvc.perform(get(URL_VEHICLES + SLASH + BRAND_BYD + URL_COUNT_BRAND)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(FOUR))
                .andExpect(jsonPath("$.message").value(VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }

    @Test
    void shouldReturnSuccessfullyWhenNoCarsRegisteredLastWeek() throws Exception {

        final Pageable pageable = PageRequest.of(ZERO, ONE);

        final Page<VehicleGetResponse> page = new PageImpl<>(List.of(getVehicleGetResponse()), pageable, ONE_LONG);

        Mockito.when(vehicleService.getCarRegistrationForLastWeek(eq(VEHICLE_CAR), eq(pageable)))
                .thenReturn(page);

        mockMvc.perform(get(URL_VEHICLES + SLASH + VEHICLE_CAR + URL_CAR_REGISTRATION_WEEK)
                        .param("page", ZERO_STRING)
                        .param("size", ONE_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenNoCarsRegisteredLastWeek() throws Exception {

        final Pageable pageable = PageRequest.of(ZERO, ONE);

        Mockito.lenient().when(vehicleService.getCarRegistrationForLastWeek(eq(VEHICLE_CAR), eq(pageable)))
                .thenThrow(NotFoundException.thereAreNoActiveVehiclesInTheBase());

        mockMvc.perform(get(URL_VEHICLES + SLASH + VEHICLE_CAR + URL_CAR_REGISTRATION_WEEK)
                        .param("page", ZERO_STRING)
                        .param("size", ONE_STRING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(FOUR))
                .andExpect(jsonPath("$.message").value(VEHICLES_DOES_NOT_EXIST_IN_THE_DATA_BASE));
    }

}
