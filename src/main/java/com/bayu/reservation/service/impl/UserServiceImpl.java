package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.ApiResponse;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.entities.Role;
import com.bayu.reservation.entities.User;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.UserConvert;
import com.bayu.reservation.repository.RoleRepository;
import com.bayu.reservation.repository.UserRepository;
import com.bayu.reservation.security.SecurityParams;
import com.bayu.reservation.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConvert userConvert;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserConvert userConvert, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConvert = userConvert;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAll() {
        List<User> userList = userRepository.findAll();
        return userConvert.entityToDto(userList);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", "id", id));
        return userConvert.entityToDto(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User", "email", email));
        return userConvert.entityToDto(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User", "username", username));
        return userConvert.entityToDto(user);
    }

    @Override
    public ApiResponse signUp(UserDTO signUpRequest) {
        // check  exists by username
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ApiResponse(400, "Bad Request", "Username is already taken");
        }

        // check exists by email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ApiResponse(400, "Bad Request", "Email is already taken");
        }

        User user = userConvert.dtoToEntity(signUpRequest);
        user.setActive(true);
        user.setRoles(Collections.singleton(roleRepository.findByName(SecurityParams.USER)));
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);

        return new ApiResponse(201, "Created", "User successfully registered", user);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userConvert.dtoToEntity(userDTO);
        user.setRoles(Collections.singleton(roleRepository.findByName(SecurityParams.USER)));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        return userConvert.entityToDto(user);
    }

    @Override
    public List<UserDTO> saveUsers(List<UserDTO> userDTOS) {
        List<User> users = userConvert.dtoToEntity(userDTOS);
        return userConvert.entityToDto(userRepository.saveAll(users));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));

        if (Objects.nonNull(userDTO.getEmail()) && !"".equalsIgnoreCase(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (Objects.nonNull(userDTO.getPassword()) && !"".equalsIgnoreCase(userDTO.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (Objects.nonNull(userDTO.getUsername()) && !"".equalsIgnoreCase(userDTO.getUsername())) {
            user.setUsername(userDTO.getUsername());
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return userConvert.entityToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User", "username", username));
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public List<UserDTO> fetchUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        List<User> userList = userRepository.findByRolesId(role.getId());
        return userConvert.entityToDto(userList);
    }

    @Override
    public UserDTO getUserByBookingId(Long bookingId) {
        User user = userRepository.findByBookingsId(bookingId).orElseThrow(() -> new NotFoundException("User", "bookingId", bookingId));
        return userConvert.entityToDto(user);
    }

}
