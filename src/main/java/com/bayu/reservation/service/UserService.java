package com.bayu.reservation.service;

import com.bayu.reservation.dto.ApiResponse;
import com.bayu.reservation.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAll();

    UserDTO getUserById(Long id);

    UserDTO getUserByEmail(String email);

    UserDTO getUserByUsername(String username);

    ApiResponse signUp(UserDTO signUpRequest);

    UserDTO save(UserDTO userDTO);

    List<UserDTO> saveUsers(List<UserDTO> userDTOS);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    void addRoleToUser(String username, String roleName);

    List<UserDTO> fetchUsersByRole(String roleName);

    UserDTO getUserByBookingId(Long bookingId);
}
