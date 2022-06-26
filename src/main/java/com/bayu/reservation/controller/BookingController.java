package com.bayu.reservation.controller;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.service.UserService;
import com.bayu.reservation.util.Form;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService) {
        this.bookingService = bookingService;
        this.userService = userService;
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

    @DeleteMapping(value = "/code/{bookingCode}")
    public ResponseEntity<String> deleteBookingByCode(@PathVariable(name = "bookingCode") String code) {
        bookingService.deleteBookingByCode(code);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @PostMapping("/create/booking/room")
    public ResponseEntity<String> bookARoom(@RequestBody Form.UserBookingForm form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO loggedInUser = userService.getUserByUsername(authentication.getPrincipal().toString());
        String s = bookingService.bookRoom(form, loggedInUser.getId());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @DeleteMapping("/booking/cancel/{code}")
    public ResponseEntity<String> cancelBooking(@PathVariable(name = "code") String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
        String s = bookingService.cancelBooking(loggedInUser.getId(), code);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/booking/reservation")
    public ResponseEntity<List<BookingDTO>> getReservations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
        List<BookingDTO> reservations = bookingService.getReservations(loggedInUser.getId());
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/booking/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByUserId(@PathVariable(name = "userId") Long userId) {
        List<BookingDTO> bookings = bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/booking/room/{roomId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByRoomId(@PathVariable(name = "roomId") Long roomId) {
        List<BookingDTO> bookingDTOS = bookingService.listBooksByRoomId(roomId);
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }
}
