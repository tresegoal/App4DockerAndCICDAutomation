package com.blitech.app4docker.utils;

import com.blitech.app4docker.dto.EmployeeDTO;
import com.blitech.app4docker.model.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @author Martin_Tresor
 */
public class EmployeeBuilder {

    public static List<EmployeeDTO> getListDTO() {
    return Arrays.asList(
            new EmployeeDTO(1,"Martin","IT",500000),
            new EmployeeDTO(2,"Ernest","RH",650000),
            new EmployeeDTO(3,"Gaelle","Direction",850000)
    );
    }

    public static List<Employee> getListEntities() {
        return Arrays.asList(
                new Employee(1,"Martin","IT",500000),
                new Employee(2,"Ernest","RH",650000),
                new Employee(3,"Gaelle","Direction",850000)
        );
    }

    public static EmployeeDTO getDTO() {
        return new EmployeeDTO(1,"Martin","IT",500000);
    }

    public static Employee getEntity() {
        return new Employee(1,"Martin","IT",500000);
    }
}
