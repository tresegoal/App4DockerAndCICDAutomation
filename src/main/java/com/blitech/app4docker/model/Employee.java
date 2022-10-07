package com.blitech.app4docker.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Martin_Tresor
 */
@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String dept;
    private double salary;

    @Column(name = "creation_date")
    private Date creationDate;

    public Employee() {
    }

    public Employee(int id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @PrePersist
    public void prePersist() {
        setCreationDate(new Date(System.currentTimeMillis()));
    }
}
