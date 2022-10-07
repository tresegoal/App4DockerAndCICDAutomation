package com.blitech.app4docker.dto;

import java.sql.Date;

public class EmployeeDTO {
    private int id;
    private String name;
    private String dept;
    private double salary;
    private Date creationDate;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return this.dept;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }

    public java.sql.Date getCreationDate() {
        return this.creationDate;
    }
}