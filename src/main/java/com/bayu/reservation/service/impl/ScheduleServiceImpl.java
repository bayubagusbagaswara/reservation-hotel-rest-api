package com.bayu.reservation.service.impl;

import com.bayu.reservation.entities.Booking;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.repository.RoomRepository;
import com.bayu.reservation.service.EmailSenderService;
import com.bayu.reservation.service.ScheduleService;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Component
public class ScheduleServiceImpl implements ScheduleService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final EmailSenderService emailSenderService;

    public ScheduleServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, EmailSenderService emailSenderService) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.emailSenderService = emailSenderService;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    @Override
    public void job1() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> activeBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getEndDate().plusMinutes(5).isAfter(currentDate)) {
                activeBookings.add(booking);
            }
        }

        for (Booking activeBooking : activeBookings) {
            if (activeBooking.getEndDate().truncatedTo(ChronoUnit.SECONDS).isEqual(currentDate.truncatedTo(ChronoUnit.SECONDS))) {
                String body = "your room " + activeBooking.getRoom().getName() + " is now free, booking " + activeBooking.getCode() + " has ended ";
                String to = activeBooking.getUser().getEmail();
                String subject = "Reservation completed";
                try {
                    emailSenderService.sendEmail(to, body, subject);
                } catch (MailException mailException) {
                    mailException.printStackTrace();
                }
                activeBooking.getRoom().setReserved(false);
                roomRepository.save(activeBooking.getRoom());
            }
        }
        System.out.println("Ran Job at " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    @Scheduled(cron = "0 0/5 * * * *")
    @Override
    public void job2() {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> bookingList = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getStartDate().plusMinutes(5).isAfter(currentDate)) {
                bookingList.add(booking);
                System.out.println(booking.getRoom().getName());
            }
        }

        for (Booking activeBooking : bookingList) {
            if (activeBooking.getStartDate().truncatedTo(ChronoUnit.SECONDS).isEqual(currentDate.truncatedTo(ChronoUnit.SECONDS)) && activeBooking.isConfirmed()) {
                System.out.println(activeBooking.getRoom().getName());
                activeBooking.getRoom().setReserved(true);
                roomRepository.save(activeBooking.getRoom());
            }
        }
        System.out.println("Ran Job 2 at " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

}
