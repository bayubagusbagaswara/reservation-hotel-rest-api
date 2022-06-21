package com.bayu.reservation.controller;

import com.bayu.reservation.dto.ApiResponse;
import com.bayu.reservation.dto.RoleDTO;
import com.bayu.reservation.service.RoleService;
import com.bayu.reservation.service.UserService;
import lombok.Data;
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

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getRoleByName(@PathVariable(name = "name") String name) {
        RoleDTO roleDTO = roleService.getRoleByName(name);
        return new ResponseEntity<>(new ApiResponse(200, "OK", "Success get role by name : " + name, roleDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public RoleDTO addNewRole(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @PostMapping(value = "/save/all")
    public List<RoleDTO> addNewRoles(@RequestBody List<RoleDTO> roleDTOS) {
        return roleService.saveRoles(roleDTOS);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteRole(@PathVariable(name = "id") Long roleId) {
        roleService.deleteRole(roleId);
        return "Deleted successfully";
    }

    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.roleName);
        return ResponseEntity.ok().build();
    }

    @Data
    public static class RoleToUserForm {
        private String username;
        private String roleName;
    }
}
