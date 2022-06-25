package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.entities.Booking;
import com.bayu.reservation.entities.Room;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.BookingConvert;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.repository.RoomRepository;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.util.Form;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

//    private final static int MIN_VALUE = 100000000;

    private final BookingRepository bookingRepository;
    private final BookingConvert bookingConvert;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingConvert bookingConvert, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingConvert = bookingConvert;
        this.roomRepository = roomRepository;
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
        bookingRepository.deleteByCode(code);
    }

    @Override
    public BookingDTO getBookingByCode(String code) {
        Booking booking = bookingRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Booking", "code", code));
        return bookingConvert.entityToDto(booking);
    }

    @Override
    public BookingDTO bookRoom(String roomName, LocalDateTime startDate, LocalDateTime endDate) {
        Room room = roomRepository.findByName(roomName).orElseThrow(() -> new NotFoundException("Room", "name", roomName));

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setCode(UUID.randomUUID().toString().replace("-",""));

        return bookingConvert.entityToDto(booking);
    }

    @Override
    public String nextBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        Booking next = new Booking();

        int minValue = 100000000;

        for (Booking booking : bookings) {
            if (booking.getStartDate().isAfter(LocalDateTime.now()) && booking.isConfirmed()) {
                int duration = (int) Duration.between(LocalDateTime.now(), booking.getStartDate()).toMinutes();
                if (duration < minValue) {
                    minValue = duration;
                    next = booking;
                }
                System.out.println(duration);
            }
        }
        return next.getCode();
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
