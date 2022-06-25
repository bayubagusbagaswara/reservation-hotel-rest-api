package com.bayu.reservation.service;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.util.Form;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<BookingDTO> listAll();

    BookingDTO getBookingById(Long bookingId);

    void deleteBookingByCode(String code);

    BookingDTO getBookingByCode(String code);

    BookingDTO bookRoom(String roomName, LocalDateTime startDate, LocalDateTime endDate);

    String nextBooking();

    List<BookingDTO> getAllBookingsByRoomName(String roomName);

    String bookRoom(Form.UserBookingForm form, Long id);

    String cancelBooking(Long id, String Code);

    List<BookingDTO> getReservations(Long id);

    List<BookingDTO> getBookingsByUserId(Long userId);

    BookingDTO save(BookingDTO bookingDTO);

    void confirmBooking(Long id, boolean isConfirmed);

    List<BookingDTO> saveDepartments(List<BookingDTO> bookingDTOList);

    String deleteBooking(Long bookingId);

    List<BookingDTO> listBooksByRoomId(Long roomId);
}
