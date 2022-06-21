package com.bayu.reservation.controller;

import com.bayu.reservation.dto.DepartmentDTO;
import com.bayu.reservation.dto.RoomDTO;
import com.bayu.reservation.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departmentDTOS = departmentService.listAll();
        return ResponseEntity.ok().body(departmentDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable(name = "id") Long departmentId) {
        DepartmentDTO departmentDTO = departmentService.getById(departmentId);
        return ResponseEntity.ok().body(departmentDTO);
    }

    @GetMapping(value = "/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> fetchRoomsByDepartmentId(@PathVariable(name = "id") Long departmentId) {
        List<RoomDTO> roomDTOS = departmentService.getRoomListByDepartmentId(departmentId);
        return ResponseEntity.ok().body(roomDTOS);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@PathVariable(name = "name") String name) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentByName(name);
        return ResponseEntity.ok().body(departmentDTO);
    }
}
