package com.bayu.reservation.service;

import com.bayu.reservation.dto.DepartmentDTO;
import com.bayu.reservation.dto.RoomDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDTO> listAll();

    DepartmentDTO getById(Long departmentId);

    // room
    List<RoomDTO> getRoomListByDepartmentId(Long departmentId);

    DepartmentDTO getDepartmentByName(String name);

    DepartmentDTO save(DepartmentDTO departmentDTO);

    List<DepartmentDTO> saveDepartments(List<DepartmentDTO> departmentDTOList);

    String deleteDepartment(Long id);

    DepartmentDTO updateDepartment(Long departmentId, DepartmentDTO departmentDTO);
}
