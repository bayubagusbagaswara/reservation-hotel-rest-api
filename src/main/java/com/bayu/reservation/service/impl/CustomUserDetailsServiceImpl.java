package com.bayu.reservation.service.impl;

import com.bayu.reservation.entities.User;
import com.bayu.reservation.repository.UserRepository;
import com.bayu.reservation.service.CustomUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));
//
//        return UserPrincipal.createUserPrincipal(user);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username: %s", username)));

        // ambil semua role dari User lalu masukkan ke authorities, karena kita mau passing ke UserDetails
        Collection<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        // balikan object UserDetails, dengan isi datanya adalah username, password, dan authorities
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
