package com.bayu.reservation.service;

import com.bayu.reservation.dto.BookingDTO;
import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.dto.UserDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> listAll();

    List<RoomDTO> listAvailable();

    RoomDTO getById(Long id);

    List<RoomDTO> saveRooms(List<RoomDTO> roomDTOList);

    // user
    List<UserDTO> getUsersByRoom(Long roomId);

    RoomDTO getRoomByName(String name);

    RoomDTO save();

    List<RoomDTO> findAvailable(String startDate, String endDate);

    // booking
    List<BookingDTO> listBookByRoom(Long roomId);

    // berapa kali room di booking
    RoomDTO mostBookedRoom();

    RoomDTO getLastReservedRoom();

    void deleteRoom(Long id);

    RoomDTO updateRoom(Long id, RoomDTO roomDTO);

}
