package com.bayu.reservation.controller;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> roomDTOS = roomService.listAll();
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<RoomDTO>> getAvailableRooms() {
        List<RoomDTO> roomDTOS = roomService.listAvailable();
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable(name = "id") Long roomId) {
        RoomDTO roomDTO = roomService.getById(roomId);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

}
