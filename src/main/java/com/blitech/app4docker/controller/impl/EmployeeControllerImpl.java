package com.blitech.app4docker.controller.impl;

import com.blitech.app4docker.controller.EmployeeController;
import com.blitech.app4docker.dto.EmployeeDTO;
import com.blitech.app4docker.mapper.EmployeeMapper;
import com.blitech.app4docker.model.Employee;
import com.blitech.app4docker.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/employee")
@RestController
public class EmployeeControllerImpl implements EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeControllerImpl(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.asEntity(employeeDTO);
        return employeeMapper.asDTO(employeeService.save(employee));
    }

    @Override
    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable("id") int id) {
        Employee employee = employeeService.findById(id).orElse(null);
        return employeeMapper.asDTO(employee);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<EmployeeDTO> list() {
        return employeeMapper.asDTOList(employeeService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<EmployeeDTO> pageQuery(Pageable pageable) {
        Page<Employee> employeePage = employeeService.findAll(pageable);
        List<EmployeeDTO> dtoList = employeePage
                .stream()
                .map(employeeMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, employeePage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") int id) {
        Employee employee = employeeMapper.asEntity(employeeDTO);
        return employeeMapper.asDTO(employeeService.update(employee, id));
    }
}