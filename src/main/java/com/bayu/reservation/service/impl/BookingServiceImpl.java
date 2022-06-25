package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.entities.Booking;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.BookingConvert;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.util.Form;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConvert bookingConvert;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingConvert bookingConvert) {
        this.bookingRepository = bookingRepository;
        this.bookingConvert = bookingConvert;
    }

    @Override
    public List<BookingDTO> listAll() {
        return bookingConvert.entityToDto(bookingRepository.findAll());
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking", "id", bookingId));
        return bookingConvert.entityToDto(booking);
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
