package com.blitech.app4docker.controller;

import com.blitech.app4docker.dto.DepartmentDto;
import com.blitech.app4docker.mapper.DepartmentMapper;
import com.blitech.app4docker.model.Department;
import com.blitech.app4docker.service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/department")
@RestController

@Api("department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated DepartmentDto departmentDto) {
        departmentService.save(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable("id") Long id) {
        DepartmentDto department = departmentService.findById(id);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<DepartmentDto>> pageQuery(DepartmentDto departmentDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DepartmentDto> departmentPage = departmentService.findByCondition(departmentDto, pageable);
        return ResponseEntity.ok(departmentPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated DepartmentDto departmentDto, @PathVariable("id") Long id) {
        departmentService.update(departmentDto, id);
        return ResponseEntity.ok().build();
    }
}