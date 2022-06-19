package com.bayu.reservation.mapper;

import com.bayu.reservation.dto.DepartmentDTO;
import com.bayu.reservation.entities.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentConvert {
    public DepartmentDTO entityToDto(Department department) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(department, DepartmentDTO.class);
    }

    public List<DepartmentDTO> entityToDto(List<Department> departments) {
        return departments.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public Department dtoToEntity(DepartmentDTO dto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(dto, Department.class);
    }

    public List<Department> dtoToEntity(List<DepartmentDTO> dto) {
        return dto.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
