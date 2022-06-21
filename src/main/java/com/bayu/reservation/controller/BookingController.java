package com.bayu.reservation.controller;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.service.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDTOS = bookingService.listAll();
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable(name = "id") Long bookingId) {
        BookingDTO bookingDTO = bookingService.getById(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/user")
    public ResponseEntity<UserDTO> getUserByBookingId(@PathVariable(name = "id") Long bookingId) {
        UserDTO userDTO = bookingService.getUserByBookingId(bookingId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<BookingDTO> getBookingByCode(@PathVariable(name = "code") String code) {
        BookingDTO bookingDTO = bookingService.getBookingByCode(code);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<BookingDTO> addBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        BookingDTO save = bookingService.save(bookingDTO);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

}
