package com.bayu.reservation.service;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<BookingDTO> listAll();

    BookingDTO getById(Long bookingId);

    void deleteBookingByCode(String code);

    BookingDTO getBookingByCode(String code);

    BookingDTO bookRoom(String roomName, LocalDateTime startDate, LocalDateTime endDate);

    String nextBooking();

    List<BookingDTO> getAllByRoomName(String roomName);

    // user
    UserDTO getUserByBookingId(Long bookingId);

    BookingDTO save(BookingDTO bookingDTO);

    void confirmBooking(Long id, boolean isConfirmed);

    List<BookingDTO> saveDepartments(List<BookingDTO> bookingDTOList);

    String deleteBooking(Long bookingId);
}
