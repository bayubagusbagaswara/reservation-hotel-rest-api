package com.bayu.reservation.controller;

import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.service.RoomService;
import com.bayu.reservation.util.Form;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        List<RoomDTO> roomDTOS = roomService.listAvailableRooms();
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable(name = "id") Long roomId) {
        RoomDTO roomDTO = roomService.getRoomById(roomId);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<RoomDTO> getRoomByName(@RequestParam(name = "name") String name) {
        RoomDTO roomDTO = roomService.getRoomByName(name);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/availability")
    public ResponseEntity<List<RoomDTO>> listAvailableRooms(@RequestParam(name = "start") String start, @RequestParam(name = "end") String end) {
        List<RoomDTO> roomDTOS = roomService.findAvailableRooms(start, end);
        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @GetMapping("/booking/latest")
    public ResponseEntity<RoomDTO> fetchLatestReservedRoom() {
        RoomDTO roomDTO = roomService.getLastReservedRoom();
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @GetMapping("/mostly/booked")
    public ResponseEntity<RoomDTO> getMostBookedRoom() {
        RoomDTO roomDTO = roomService.mostBookedRoom();
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody Form.RoomForm room) {
        RoomDTO save = roomService.save(room);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/save").toUriString());
        return ResponseEntity.created(uri).body(save);
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<RoomDTO>> addRooms(@RequestBody List<RoomDTO> rooms) {
        List<RoomDTO> roomDTOS = roomService.saveRooms(rooms);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/save/all").toUriString());
        return ResponseEntity.created(uri).body(roomDTOS);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable(name = "id") Long roomId, @RequestBody RoomDTO roomDTO) {
        RoomDTO updateRoom = roomService.updateRoom(roomId, roomDTO);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/room/{id}").toUriString());
        return ResponseEntity.created(uri).body(updateRoom);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteRoom(@PathVariable(name = "id") Long roomId) {
        roomService.deleteRoom(roomId);
        return "Deleted successfully";
    }

    @GetMapping(value = "/department/{departmentId}")
    public ResponseEntity<List<RoomDTO>> getRoomListByDepartmentId(@PathVariable(name = "departmentId") Long departmentId) {
        List<RoomDTO> roomDTOList = roomService.getRoomListByDepartmentId(departmentId);
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }
}
