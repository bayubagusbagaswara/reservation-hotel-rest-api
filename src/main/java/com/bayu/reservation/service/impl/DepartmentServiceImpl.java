package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.DepartmentDTO;
import com.bayu.reservation.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<DepartmentDTO> listAll() {
        return null;
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        return null;
    }

    @Override
    public DepartmentDTO getDepartmentByName(String name) {
        return null;
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        return null;
    }

    @Override
    public List<DepartmentDTO> saveDepartments(List<DepartmentDTO> departmentDTOList) {
        return null;
    }

    @Override
    public String deleteDepartment(Long id) {
        return null;
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        return null;
    }
}
