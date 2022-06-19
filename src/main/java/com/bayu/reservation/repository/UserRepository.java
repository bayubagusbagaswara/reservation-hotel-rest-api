package com.bayu.reservation.repository;

import com.bayu.reservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByRoles_Id(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
