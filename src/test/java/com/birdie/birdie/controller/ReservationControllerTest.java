package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReservationService reservationService;

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
}