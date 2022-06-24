package com.bayu.reservation.controller;

import com.bayu.reservation.dto.ApiResponse;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDTO newUser) {
        ApiResponse apiResponse = userService.signUp(newUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
