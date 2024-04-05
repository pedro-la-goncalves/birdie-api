package com.birdie.birdie.booking.guest.dto;

import com.birdie.birdie.booking.guest.contact.dto.IndirectContactCreationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GuestCreationDTO {

        @NotBlank
        String name;

        String surname;

        String socialName;

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate;

        @Valid
        List<IndirectContactCreationDTO> contacts;

}
