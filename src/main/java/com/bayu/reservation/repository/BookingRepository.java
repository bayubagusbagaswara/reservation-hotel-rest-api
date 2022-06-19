package com.bayu.reservation.repository;

import com.bayu.reservation.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByCode(String code);

    void deleteByCode(String code);
}
