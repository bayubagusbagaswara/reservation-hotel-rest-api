package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.RoleDTO;
import com.bayu.reservation.entities.Role;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.RoleConvert;
import com.bayu.reservation.repository.RoleRepository;
import com.bayu.reservation.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConvert roleConverter;

    public RoleServiceImpl(RoleRepository roleRepository, RoleConvert roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleConverter.dtoToEntity(roleDTO);
        roleRepository.save(role);
        return roleConverter.entityToDto(role);
    }

    @Override
    public List<RoleDTO> saveRoles(List<RoleDTO> roleDTOS) {
        List<Role> roles = roleConverter.dtoToEntity(roleDTOS);
        return roleConverter.entityToDto(roleRepository.saveAll(roles));
    }

    @Override
    public List<RoleDTO> listAll() {
        return roleConverter.entityToDto(roleRepository.findAll());
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role", "id", id));
        return roleConverter.entityToDto(role);
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        return roleConverter.entityToDto(roleRepository.findByName(name));
    }

    @Override
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role", "id", id));
        roleRepository.delete(role);
    }
}
