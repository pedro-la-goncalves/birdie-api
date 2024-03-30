package com.birdie.birdie.controller;

import com.birdie.birdie.dto.CreateReservationDTO;
import com.birdie.birdie.dto.GuestDTO;
import com.birdie.birdie.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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

    @Test
    void givenValidValues_whenCreatingANewReservation_thenReturnSuccess() throws Exception {
        GuestDTO guestDTO = new GuestDTO(
                0L,
                "John Doe",
                "507.603.730-94",
                "+00 (00) 0 0000-0000"
        );

        CreateReservationDTO createReservationDTO = new CreateReservationDTO(
                guestDTO,
                LocalDate.now(),
                LocalDate.now().plusDays(2),
                false
        );

        this.mockMvc
                .perform(post("/api/reservations")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createReservationDTO))
                )
                .andExpect(status().isOk());
    }
}