package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.mapper.BookingConvert;
import com.bayu.reservation.mapper.UserConvert;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.repository.RoleRepository;
import com.bayu.reservation.repository.UserRepository;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.service.EmailSenderService;
import com.bayu.reservation.service.UserService;
import com.bayu.reservation.util.Form;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final RoleRepository roleRepository;
    private final BookingService bookingService;
    private final UserConvert userConvert;
    private final BookingConvert bookingConvert;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, RoleRepository roleRepository, BookingService bookingService, UserConvert userConvert, BookingConvert bookingConvert, PasswordEncoder passwordEncoder, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.roleRepository = roleRepository;
        this.bookingService = bookingService;
        this.userConvert = userConvert;
        this.bookingConvert = bookingConvert;
        this.passwordEncoder = passwordEncoder;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public List<UserDTO> listAll() {
        return null;
    }

    @Override
    public UserDTO getById(Long id) {
        return null;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO signUp(UserDTO signUpRequest) {
        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> saveUsers(List<UserDTO> userDTOS) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public List<UserDTO> fetchUsersByRole(String roleName) {
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
