package com.birdie.birdie.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateReservationDTO(
        @JsonAlias("guest_id") String guestId,
        @JsonAlias("scheduled_entry") String scheduledEntry,
        @JsonAlias("scheduled_departure") String scheduledDeparture,
        @JsonAlias("parking") boolean parking
) {}
