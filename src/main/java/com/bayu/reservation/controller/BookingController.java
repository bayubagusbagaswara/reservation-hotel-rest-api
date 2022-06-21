package com.bayu.reservation.controller;

import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.service.EmailSenderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    private final EmailSenderService emailSenderService;

    public BookingController(BookingService bookingService, EmailSenderService emailSenderService) {
        this.bookingService = bookingService;
        this.emailSenderService = emailSenderService;
    }
}
