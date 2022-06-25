package com.bayu.reservation.controller;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.service.UserService;
import com.bayu.reservation.util.Form;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> userDTOS = userService.listAll();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> findUserById(@PathVariable(name = "id") Long id) {
//        UserDTO userDTO = userService.getById(id);
//        return new ResponseEntity<>(userDTO, HttpStatus.OK);
//    }

//    @GetMapping(value = "/bookings")
//    public ResponseEntity<List<BookingDTO>> getReservations() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
//        List<BookingDTO> reservations = userService.getReservations(loggedInUser.getId());
//        return new ResponseEntity<>(reservations, HttpStatus.OK);
//    }


//    @PostMapping(value = "/book")
//    public ResponseEntity<String> bookARoom(@Valid @RequestBody Form.UserBookingForm form) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
//        String s = userService.bookRoom(form, loggedInUser.getId());
//        return new ResponseEntity<>(s, HttpStatus.OK);
//    }

    @GetMapping(value = "/findEmail/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable(name = "email") String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO newUser) {
        UserDTO userDTO = userService.save(newUser);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PostMapping(value = "/save/all")
    public ResponseEntity<List<UserDTO>> addUsers(@Valid @RequestBody List<UserDTO> newUserList) {
        List<UserDTO> userDTOS = userService.saveUsers(newUserList);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save/all").toUriString());
        return ResponseEntity.created(uri).body(userDTOS);
    }

//    @DeleteMapping(value = "/booking/cancel/{code}")
//    public ResponseEntity<String> cancelBooking(@PathVariable(name = "code") String code) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
//        String string = userService.cancelBooking(loggedInUser.getId(), code);
//        return new ResponseEntity<>(string, HttpStatus.OK);
//    }

    // 1 user bisa nge-booking banyak kamar
//    @GetMapping(value = "/{id}/bookings")
//    public ResponseEntity<List<BookingDTO>> fetchBookingsByUserId(@PathVariable(name = "id") Long userId) {
//        List<BookingDTO> bookingDTOList = userService.getBookingsByUserId(userId);
//        return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
//    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody UserDTO user) {
        UserDTO userDTO = userService.updateUser(userId, user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/update/{id}").toUriString());
        return ResponseEntity.created(uri).body(userDTO);
    }

//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO loggedInUser = userService.getUserByUsername(auth.getPrincipal().toString());
//        if (loggedInUser.getId().equals(userService.getById(id).getId())) {
//            return ResponseEntity.status(405).body("cannot delete current logged in user");
//        } else {
//            userService.deleteUser(id);
//            return ResponseEntity.ok().body("User deleted !");
//        }
//    }


//    @GetMapping("/token/refresh")
//    public void TokenRefresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        service.refreshToken(request, response);
//    }


}

