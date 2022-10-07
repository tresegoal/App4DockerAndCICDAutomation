package com.blitech.app4docker.mapper;

import com.blitech.app4docker.dto.EmployeeDTO;
import com.blitech.app4docker.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends GenericMapper<Employee, EmployeeDTO> {
    @Override
    @Mapping(target = "id", ignore = true)
    Employee asEntity(EmployeeDTO dto);
}