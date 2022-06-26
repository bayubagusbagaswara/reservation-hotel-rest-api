package com.bayu.reservation.service;

import com.bayu.reservation.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> listAll();

    DepartmentDTO getDepartmentById(Long departmentId);

    DepartmentDTO getDepartmentByName(String name);

    DepartmentDTO save(DepartmentDTO departmentDTO);

    List<DepartmentDTO> saveDepartments(List<DepartmentDTO> departmentDTOList);

    String deleteDepartment(Long id);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
}
