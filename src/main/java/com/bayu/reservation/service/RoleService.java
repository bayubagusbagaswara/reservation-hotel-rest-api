package com.bayu.reservation.service;

import com.bayu.reservation.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO save(RoleDTO roleDTO);

    List<RoleDTO> saveRoles(List<RoleDTO> roleDTOS);

    List<RoleDTO> listAll();

    RoleDTO getRoleById(Long id);

    RoleDTO getRoleByName(String name);

    void deleteRole(Long id);

}
