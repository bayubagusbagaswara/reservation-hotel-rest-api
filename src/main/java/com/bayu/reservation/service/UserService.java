package com.bayu.reservation.service;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.util.Form;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDTO> listAll();

    UserDTO getById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    UserDTO signUp(UserDTO signUpRequest);

    UserDTO save(UserDTO userDTO);

    List<UserDTO> saveUsers(List<UserDTO> userDTOS);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    void addRoleToUser(String username, String roleName);

    List<UserDTO> fetchUsersByRole(String roleName);

    String bookRoom(Form.UserBookingForm form, Long id);

    String cancelBooking(Long id, String Code);

    List<BookingDTO> getReservations(Long id);

    List<BookingDTO> getBookingsByUserId(Long id);

}
