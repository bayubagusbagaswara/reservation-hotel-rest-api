package com.bayu.reservation.controller;

import com.bayu.reservation.dto.RoleDTO;
import com.bayu.reservation.service.RoleService;
import com.bayu.reservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleDTO>> findAllRoles() {
        List<RoleDTO> roleDTOS = roleService.listAll();
        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<RoleDTO> findRoleById(@PathVariable(name = "id") Long roleId) {
        RoleDTO roleDTO = roleService.getById(roleId);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }


}
