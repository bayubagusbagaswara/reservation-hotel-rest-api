package com.bayu.reservation.controller;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.service.BookingService;
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
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
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

    @GetMapping(value = "/all/room")
    public ResponseEntity<List<BookingDTO>> fetchBookingsByRoomName(@RequestBody String name) {
        List<BookingDTO> bookingDTOS = bookingService.getAllBookingsByRoomName(name);
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping("/next")
    public ResponseEntity<String> getNextBooking() {
        String s = bookingService.nextBooking();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<BookingDTO>> addBookings(@RequestBody List<BookingDTO> bookingDTOS) {
        List<BookingDTO> bookingDTOList = bookingService.saveDepartments(bookingDTOS);
        return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirmBooking(@RequestBody boolean confirmed, @PathVariable(name = "id") Long bookingId) {
        bookingService.confirmBooking(bookingId, confirmed);
        if (confirmed)
            return ResponseEntity.ok().body("confirmed");
        else {
            return ResponseEntity.ok().body("not confirmed!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable(name = "id") Long bookingId) {
        String s = bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

}
