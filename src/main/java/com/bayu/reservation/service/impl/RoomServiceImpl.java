package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.entities.Room;
import com.bayu.reservation.mapper.RoomConvert;
import com.bayu.reservation.repository.RoomRepository;
import com.bayu.reservation.service.RoomService;
import com.bayu.reservation.util.Form;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomConvert roomConvert;

    public RoomServiceImpl(RoomRepository roomRepository, RoomConvert roomConvert) {
        this.roomRepository = roomRepository;
        this.roomConvert = roomConvert;
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
        return null;
    }

    @Override
    public List<RoomDTO> saveRooms(List<RoomDTO> roomDTOList) {
        return null;
    }

    @Override
    public RoomDTO getRoomByName(String roomName) {
        return null;
    }

    @Override
    public RoomDTO save(Form.RoomForm form) {
        return null;
    }

    @Override
    public List<RoomDTO> findAvailable(String startDate, String endDate) {
        return null;
    }

    @Override
    public RoomDTO mostBookedRoom() {
        return null;
    }

    @Override
    public RoomDTO getLastReservedRoom() {
        return null;
    }

    @Override
    public void deleteRoom(Long roomId) {

    }

    @Override
    public RoomDTO updateRoom(Long roomId, RoomDTO roomDTO) {
        return null;
    }

    @Override
    public List<RoomDTO> getRoomListByDepartmentId(Long departmentId) {
        return null;
    }
}
