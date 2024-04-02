package com.birdie.birdie.booking.guest.dto;

import com.birdie.birdie.booking.guest.contact.dto.ContactIndirectCreationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GuestCreationDTO {

        @NotBlank
        String name;

        String surname;

        String socialName;

        @Valid
        List<ContactIndirectCreationDTO> contacts;

}
