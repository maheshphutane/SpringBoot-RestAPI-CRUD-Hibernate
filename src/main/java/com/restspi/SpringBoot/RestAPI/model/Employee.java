package com.restspi.SpringBoot.RestAPI.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emp_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    private String email;
}
