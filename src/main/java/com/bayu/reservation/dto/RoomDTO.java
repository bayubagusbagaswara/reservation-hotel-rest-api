package com.bayu.reservation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Long id;

    private String name;

    private boolean reserved = false;

    private int counter = 0;

    private DepartmentDTO department;

    @JsonIgnore
    private Collection<BookingDTO> bookings;
}
