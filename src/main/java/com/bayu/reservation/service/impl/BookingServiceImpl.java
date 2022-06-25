package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.util.Form;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Override
    public List<BookingDTO> listAll() {
        return null;
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public void deleteBookingByCode(String code) {

    }

    @Override
    public BookingDTO getBookingByCode(String code) {
        return null;
    }

    @Override
    public BookingDTO bookRoom(String roomName, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public String nextBooking() {
        return null;
    }

    @Override
    public List<BookingDTO> getAllByRoomName(String roomName) {
        return null;
    }

    @Override
    public String bookRoom(Form.UserBookingForm form, Long id) {
        return null;
    }

    @Override
    public String cancelBooking(Long id, String Code) {
        return null;
    }

    @Override
    public List<BookingDTO> getReservations(Long id) {
        return null;
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Long id) {
        return null;
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        return null;
    }

    @Override
    public void confirmBooking(Long id, boolean isConfirmed) {

    }

    @Override
    public List<BookingDTO> saveDepartments(List<BookingDTO> bookingDTOList) {
        return null;
    }

    @Override
    public String deleteBooking(Long bookingId) {
        return null;
    }

    @Override
    public List<BookingDTO> listBooksByRoomId(Long roomId) {
        return null;
    }
}
