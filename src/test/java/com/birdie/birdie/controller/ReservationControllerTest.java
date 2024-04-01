package com.birdie.birdie.controller;

import com.birdie.birdie.mvc.controller.ReservationController;
import com.birdie.birdie.mvc.dto.CreateReservationDTO;
import com.birdie.birdie.mvc.dto.GuestDTO;
import com.birdie.birdie.mvc.dto.UpdateReservationDTO;
import com.birdie.birdie.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservationService reservationService;

    @Test
    void whenSearchingForAllReservations_thenReturnSuccess() throws Exception {
        this.mockMvc
                .perform(get("/api/reservations")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenAValidId_whenSearchingOneReservation_thenReturnSuccess() throws Exception {
        this.mockMvc
                .perform(get("/api/reservations/999")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }

    @Test
    void givenAnInvalidId_whenSearchingOneReservation_thenReturn4xx() throws Exception {
        this.mockMvc
                .perform(get("/api/reservations/asd")
                        .contentType("application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("provideValidReservationsForCreatingNewReservations")
    void givenValidValues_whenCreatingANewReservation_thenReturnSuccess(CreateReservationDTO createReservationDTO) throws Exception {
        this.mockMvc
                .perform(post("/api/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createReservationDTO))
                )
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> provideValidReservationsForCreatingNewReservations() {
        GuestDTO guestDTO = new GuestDTO(0L, "John Doe", "507.603.730-94", "+00 (00) 0 0000-0000");

        return Stream.of(
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now(), LocalDate.now().plusDays(2),false)),
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now(), LocalDate.now().plusDays(2),true)),
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now(), LocalDate.now().plusMonths(2),true))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidReservationsForCreatingNewReservations")
    void givenInvalidValues_whenCreatingANewReservation_thenReturn4xx(CreateReservationDTO createReservationDTO) throws Exception {
        this.mockMvc
                .perform(post("/api/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createReservationDTO))
                )
                .andExpect(status().is4xxClientError());
    }

    private static Stream<Arguments> provideInvalidReservationsForCreatingNewReservations() {
        GuestDTO guestDTO = new GuestDTO(0L, "John Doe", "507.603.730-94", "+00 (00) 0 0000-0000");

        return Stream.of(
                Arguments.of(new CreateReservationDTO(null, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2),false)),
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2),false)),
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now(), LocalDate.now().minusDays(2),true)),
                Arguments.of(new CreateReservationDTO(guestDTO, LocalDate.now(), LocalDate.now(),true))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidReservationsForUpdatingAReservation")
    void givenValidValues_whenUpdatingAReservation_thenReturnSuccess(UpdateReservationDTO updateReservationDTO) throws Exception {
        this.mockMvc
                .perform(put("/api/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateReservationDTO))
                )
                .andExpect(status().isOk());
    }

    private static Stream<Arguments> provideValidReservationsForUpdatingAReservation() {
        GuestDTO guestDTO = new GuestDTO(0L, "John Doe", "507.603.730-94", "+00 (00) 0 0000-0000");

        return Stream.of(
                Arguments.of(new UpdateReservationDTO(0L, guestDTO, LocalDate.now(), LocalDate.now().plusDays(2),false)),
                Arguments.of(new UpdateReservationDTO(0L, guestDTO, null, null,false))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidReservationsForUpdatingAReservation")
    void givenInvalidValues_whenUpdatingAReservation_thenReturn4xx(UpdateReservationDTO updateReservationDTO) throws Exception {
        this.mockMvc
                .perform(put("/api/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateReservationDTO))
                )
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidReservationsForUpdatingAReservation() {
        GuestDTO guestDTO = new GuestDTO(0L, "John Doe", "507.603.730-94", "+00 (00) 0 0000-0000");

        return Stream.of(
                Arguments.of(new UpdateReservationDTO(0L, guestDTO, null, LocalDate.now().minusDays(2),false)),
                Arguments.of(new UpdateReservationDTO(0L, guestDTO, LocalDate.now().minusDays(2), null,false))
        );
    }

    @Test
    void givenAValidId_whenDeletingOneReservation_thenReturnSuccess() throws Exception {
        this.mockMvc
                .perform(delete("/api/reservations/999")
                        .contentType("application/json")
                )
                .andExpect(status().isOk());
    }
}