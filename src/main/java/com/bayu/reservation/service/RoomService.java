package com.bayu.reservation.service;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.util.Form;

import java.util.List;

public interface RoomService {

    List<RoomDTO> listAll();

    List<RoomDTO> listAvailable();

    RoomDTO getById(Long id);

    List<RoomDTO> saveRooms(List<RoomDTO> roomDTOList);

    RoomDTO getRoomByName(String name);

    RoomDTO save(Form.RoomForm form);

    List<RoomDTO> findAvailable(String startDate, String endDate);

    // berapa kali room di booking
    RoomDTO mostBookedRoom();

    RoomDTO getLastReservedRoom();

    void deleteRoom(Long id);

    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

    List<RoomDTO> getRoomListByDepartmentId(Long departmentId);

}
