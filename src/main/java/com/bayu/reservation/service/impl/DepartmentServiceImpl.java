package com.bayu.reservation.service.impl;

import com.bayu.reservation.dto.DepartmentDTO;
import com.bayu.reservation.entities.Department;
import com.bayu.reservation.exception.NotFoundException;
import com.bayu.reservation.mapper.DepartmentConvert;
import com.bayu.reservation.repository.DepartmentRepository;
import com.bayu.reservation.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentConvert departmentConvert;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentConvert departmentConvert) {
        this.departmentRepository = departmentRepository;
        this.departmentConvert = departmentConvert;
    }

    @Override
    public List<DepartmentDTO> listAll() {
        return departmentConvert.entityToDto(departmentRepository.findAll());
    }

    @Override
    public DepartmentDTO getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException("Department", "id", departmentId));
        return departmentConvert.entityToDto(department);
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
