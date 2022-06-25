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
import java.util.Objects;

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
        Department department = departmentRepository.findByName(name).orElseThrow(() -> new NotFoundException("Department", "name", name));
        return departmentConvert.entityToDto(department);
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = departmentConvert.dtoToEntity(departmentDTO);
        departmentRepository.save(department);
        return departmentConvert.entityToDto(department);
    }

    @Override
    public List<DepartmentDTO> saveDepartments(List<DepartmentDTO> departmentDTOList) {
        List<Department> departments = departmentConvert.dtoToEntity(departmentDTOList);
        return departmentConvert.entityToDto(departmentRepository.saveAll(departments));
    }

    @Override
    public String deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department", "id", id));
        departmentRepository.delete(department);
        return "Deleted Successfully";
    }

    @Override
    public DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException("Department", "id", departmentId));

        if (Objects.nonNull(departmentDTO.getName()) && !"".equalsIgnoreCase(departmentDTO.getName())) {
            department.setName(departmentDTO.getName());
        }
        departmentRepository.save(department);
        return departmentConvert.entityToDto(department);
    }
}
