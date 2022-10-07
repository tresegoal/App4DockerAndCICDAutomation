package com.blitech.app4docker.controller;

import com.blitech.app4docker.dto.DepartmentDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class DepartmentBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static DepartmentDto getDto() {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(1L);
        dto.setName("IT");
        dto.setDescription("Department for computer science");
        dto.setSize(50);
        return dto;
    }
}