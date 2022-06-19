package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.RoleDTO;
import com.bayu.reservation.repository.RoleRepository;
import com.bayu.reservation.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public List<RoleDTO> saveRoles(List<RoleDTO> roleDTOS) {
        return null;
    }

    @Override
    public List<RoleDTO> listAll() {
        return null;
    }

    @Override
    public RoleDTO getById(Long id) {
        return null;
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }
}
