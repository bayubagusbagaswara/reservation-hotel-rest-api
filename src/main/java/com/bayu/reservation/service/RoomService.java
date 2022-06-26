package com.bayu.reservation.service;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.util.Form;

import java.util.List;

public interface RoomService {

    List<RoomDTO> listAll();

    List<RoomDTO> listAvailableRooms();

    RoomDTO getRoomById(Long roomId);

    List<RoomDTO> saveRooms(List<RoomDTO> roomDTOList);

    RoomDTO getRoomByName(String roomName);

    RoomDTO save(Form.RoomForm form);

    List<RoomDTO> findAvailableRooms(String startDate, String endDate);

    // berapa kali room di booking
    RoomDTO mostBookedRoom();

    RoomDTO getLastReservedRoom();

    void deleteRoom(Long roomId);

    RoomDTO updateRoom(Long roomId, RoomDTO roomDTO);

    List<RoomDTO> getRoomListByDepartmentId(Long departmentId);

}
