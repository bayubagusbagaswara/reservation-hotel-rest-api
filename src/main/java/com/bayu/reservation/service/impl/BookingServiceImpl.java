package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.UserDTO;
import com.bayu.reservation.entities.Booking;
import com.bayu.reservation.entities.Room;
import com.bayu.reservation.entities.User;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.BookingConvert;
import com.bayu.reservation.mapper.UserConvert;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.repository.RoomRepository;
import com.bayu.reservation.repository.UserRepository;
import com.bayu.reservation.security.SecurityParams;
import com.bayu.reservation.service.BookingService;
import com.bayu.reservation.service.EmailSenderService;
import com.bayu.reservation.service.UserService;
import com.bayu.reservation.util.Form;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

//    private final static int MIN_VALUE = 100000000;

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BookingConvert bookingConvert;
    private final RoomRepository roomRepository;
    private final UserConvert userConvert;
    private final UserService userService;

    private final EmailSenderService emailSenderService;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, BookingConvert bookingConvert, RoomRepository roomRepository, UserConvert userConvert, UserService userService, EmailSenderService emailSenderService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.bookingConvert = bookingConvert;
        this.roomRepository = roomRepository;
        this.userConvert = userConvert;
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public List<BookingDTO> listAll() {
        return bookingConvert.entityToDto(bookingRepository.findAll());
    }

    @Override
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking", "id", bookingId));
        return bookingConvert.entityToDto(booking);
    }

    @Override
    public void deleteBookingByCode(String code) {
        bookingRepository.deleteByCode(code);
    }

    @Override
    public BookingDTO getBookingByCode(String code) {
        Booking booking = bookingRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Booking", "code", code));
        return bookingConvert.entityToDto(booking);
    }

    @Override
    public BookingDTO bookRoom(String roomName, LocalDateTime startDate, LocalDateTime endDate) {
        Room room = roomRepository.findByName(roomName).orElseThrow(() -> new NotFoundException("Room", "name", roomName));

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setCode(UUID.randomUUID().toString().replace("-",""));

        return bookingConvert.entityToDto(booking);
    }

    @Override
    public String nextBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        Booking next = new Booking();

        int minValue = 100000000;

        for (Booking booking : bookings) {
            if (booking.getStartDate().isAfter(LocalDateTime.now()) && booking.isConfirmed()) {
                int duration = (int) Duration.between(LocalDateTime.now(), booking.getStartDate()).toMinutes();
                if (duration < minValue) {
                    minValue = duration;
                    next = booking;
                }
                System.out.println(duration);
            }
        }
        return next.getCode();
    }

    @Override
    public List<BookingDTO> getAllBookingsByRoomName(String roomName) {
        Room room = roomRepository.findByName(roomName).orElseThrow(() -> new NotFoundException("Room", "name", roomName));
        List<Booking> bookings = room.getBookings();
        return bookingConvert.entityToDto(bookings);
    }

    @Override
    public String bookRoom(Form.UserBookingForm form, Long id) {
        LocalDateTime startDate = LocalDateTime.parse(form.getDateStart());
        LocalDateTime endDate = LocalDateTime.parse(form.getDateEnd());
        UserDTO userDTO = userConvert.entityToDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id)));

        BookingDTO booking = bookRoom(form.getRoomName(), startDate, endDate);
        booking.setUser(userDTO);
        booking.setDescription(form.getDescription());
        if (startDate.isAfter(endDate) || endDate.isEqual(startDate) || endDate.isBefore(LocalDateTime.now()) || startDate.isBefore(LocalDateTime.now())) {
            return "Your date does not fit criteria!";
        }
        BookingDTO bookingDTO = save(booking);

        if (!Objects.isNull(bookingDTO)) {

            Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());
            period = period.minusDays(endDate.toLocalTime().compareTo(startDate.toLocalTime()) >= 0 ? 0 : 1);
            Duration duration = Duration.between(startDate, endDate);
            duration = duration.minusDays(duration.toDays());
            int hours = (int) duration.toHours();
            String minutes = (duration.toMinutes() > 0 ? (duration.toMinutes() + " minutes") : "");
            String days = period.getDays() > 1 ? " days" : " day";
            String isday = (period.getDays() > 0 ? (period.getDays() + days) : "");
            List<User> users = userConvert.dtoToEntity(userService.fetchUsersByRole(SecurityParams.ADMIN));
            String Body = "Reservation " + booking.getCode() + ": User " + booking.getUser().getFirstName() + " has reserved room "
                    + booking.getRoom().getName() + " with a duration of "
                    + isday
                    + hours + " Hour" + (hours > 1 ? "s " : " ")
                    + minutes
                    + "\n\nReservation starts at  " + startDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")) +
                    " Please confirm  booking ID : " + bookingDTO.getId() +
                    " with a  POST request at  http://localhost:8080/" +
                    bookingDTO.getId() + "/confirm?confirmed=true";
            for (User user : users) {
                try {
                    emailSenderService.sendEmail(user.getEmail(), Body, "Confirm Booking!");
                } catch (MailException mailException) {
                    mailException.printStackTrace();
                }
            }
        }
        return "Added Successfully";
    }

    @Override
    public String cancelBooking(Long id, String code) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "id", id));
        Collection<Booking> bookings = user.getBookings();
        Booking booking = bookingRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Booking", "code", code));
        LocalDateTime now = LocalDateTime.now();
        if (Objects.isNull(booking)) {
            return "Please enter your correct reservation code!"; // response 404
        }

        if (booking.getEndDate().isBefore(now)) {
            return "You cannot cancel a booking that has already ended!"; // response 405
        }

        if (bookings.contains(booking)) {
            booking.getRoom().setReserved(false);
            user.getBookings().remove(booking);
            String s = deleteBooking(booking.getId());
            userRepository.save(user);
            List<User> users = userConvert.dtoToEntity(userService.fetchUsersByRole(SecurityParams.ADMIN));
            String Body = "User " + user.getUsername() + " has cancelled the reservation "
                    + booking.getCode() +
                    " at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")) + " regarding room : " + booking.getRoom().getName();

            for (User user1 : users) {
                try {
                    emailSenderService.sendEmail(user1.getEmail(), Body, "Booking cancelled!");
                } catch (MailException mailException) {
                    mailException.printStackTrace();
                }
            }
            return s;
        } else {
            return "Error deleting reservation that is not yours"; // response 405
        }
    }

    @Override
    public List<BookingDTO> getReservations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User", "id", userId));
        Collection<Booking> bookings = user.getBookings();
        return bookingConvert.entityToDto(bookings);
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Long id) {
        return null;
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        return null;
    }

    @Override
    public void confirmBooking(Long id, boolean isConfirmed) {

    }

    @Override
    public List<BookingDTO> saveDepartments(List<BookingDTO> bookingDTOList) {
        return null;
    }

    @Override
    public String deleteBooking(Long bookingId) {
        return null;
    }

    @Override
    public List<BookingDTO> listBooksByRoomId(Long roomId) {
        return null;
    }
}
