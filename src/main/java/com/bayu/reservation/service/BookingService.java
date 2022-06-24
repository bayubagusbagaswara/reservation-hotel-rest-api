package com.bayu.reservation.service;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.util.Form;

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

    String bookRoom(Form.UserBookingForm form, Long id);

    String cancelBooking(Long id, String Code);

    List<BookingDTO> getReservations(Long id);

    List<BookingDTO> getBookingsByUserId(Long id);

    BookingDTO save(BookingDTO bookingDTO);

    void confirmBooking(Long id, boolean isConfirmed);

    List<BookingDTO> saveDepartments(List<BookingDTO> bookingDTOList);

    String deleteBooking(Long bookingId);
}
