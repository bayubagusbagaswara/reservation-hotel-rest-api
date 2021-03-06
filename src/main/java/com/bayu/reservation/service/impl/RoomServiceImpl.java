package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.entities.Booking;
import com.bayu.reservation.entities.Department;
import com.bayu.reservation.entities.Room;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.RoomConvert;
import com.bayu.reservation.repository.BookingRepository;
import com.bayu.reservation.repository.DepartmentRepository;
import com.bayu.reservation.repository.RoomRepository;
import com.bayu.reservation.service.RoomService;
import com.bayu.reservation.util.Form;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomConvert roomConvert;
    private final DepartmentRepository departmentRepository;
    private final BookingRepository bookingRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RoomConvert roomConvert, DepartmentRepository departmentRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.roomConvert = roomConvert;
        this.departmentRepository = departmentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<RoomDTO> listAll() {
        List<Room> roomList = roomRepository.findAll();
        return roomConvert.entityToDto(roomList);
    }

    @Override
    public List<RoomDTO> listAvailableRooms() {
        List<Room> roomList = roomRepository.findAll();
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : roomList) {
            if (!room.isReserved()) {
                availableRooms.add(room);
            }
        }
        return roomConvert.entityToDto(availableRooms);
    }

    @Override
    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Room not available"));
        return roomConvert.entityToDto(room);
    }

    @Override
    public List<RoomDTO> saveRooms(List<RoomDTO> roomDTOList) {
        List<Room> rooms = roomConvert.dtoToEntity(roomDTOList);
        roomRepository.saveAll(rooms);
        return roomConvert.entityToDto(rooms);
    }

    @Override
    public RoomDTO getRoomByName(String roomName) {
        Room room = roomRepository.findByName(roomName).orElseThrow(() -> new NotFoundException("Room", "name", roomName));
        return roomConvert.entityToDto(room);
    }

    @Override
    public RoomDTO save(Form.RoomForm form) {
        Department department = departmentRepository.findById(form.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department", "id", form.getDepartmentId()));
        Room room = new Room();
        room.setName(form.getName());
        room.setReserved(false);
        room.setDepartment(department);
        roomRepository.save(room);
        return roomConvert.entityToDto(room);
    }

    @Override
    public List<RoomDTO> findAvailableRooms(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<Room> roomList = roomRepository.findMeetingRoomAvailable(start, end);
        return roomConvert.entityToDto(roomList);
    }

    @Override
    public RoomDTO mostBookedRoom() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> roomDTOS = roomConvert.entityToDto(rooms);
        return roomDTOS
                .stream()
                .max(Comparator.comparing(RoomDTO::getCounter))
                .orElseGet(RoomDTO::new)
                ;
    }

    @Override
    public RoomDTO getLastReservedRoom() {
        List<Booking> bookings = bookingRepository.findAll();
        Booking booking = bookings.get(bookings.size() - 1);
        return roomConvert.entityToDto(booking.getRoom());
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Room", "id", roomId));
        roomRepository.delete(room);
    }

    @Override
    public RoomDTO updateRoom(Long roomId, RoomDTO roomDTO) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("Room", "id", roomId));
        if (Objects.nonNull(roomDTO.getName()) && !"".equalsIgnoreCase(roomDTO.getName())) {
            room.setName(roomDTO.getName());
        }
        roomRepository.save(room);
        return roomConvert.entityToDto(room);
    }

    @Override
    public List<RoomDTO> getRoomListByDepartmentId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException("Department", "id", departmentId));
        List<Room> rooms = department.getRoom();
        return roomConvert.entityToDto(rooms);
    }
}
