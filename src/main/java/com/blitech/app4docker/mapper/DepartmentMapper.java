package com.blitech.app4docker.mapper;

import com.blitech.app4docker.dto.DepartmentDto;
import com.blitech.app4docker.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDto, Department> {
}