package com.bayu.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    @Email(message = "Email must be formatted")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 30, message = "Password minimum length 6 characters and maximum 100 characters")
    private String password;

    @JsonIgnore
    private List<RoleDTO> roles = new ArrayList<>();

    @JsonIgnore
    private List<BookingDTO> bookings;
}
