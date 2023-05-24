package com.restspi.SpringBoot.RestAPI.service;

import com.restspi.SpringBoot.RestAPI.model.Employee;
import com.restspi.SpringBoot.RestAPI.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean saveEmployeeDetails(Employee employee){
        Employee res = employeeRepository.save(employee);
        if(res!=null && res.getEmp_id()>0){
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployeeDetails(){
        List<Employee> emp_list= employeeRepository.findAll();
        return emp_list;
    }

    public Employee findEmployeeById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.get()!=null){
            return employee.get();
        }
        return null;
    }

    public boolean deleteEmpById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isEmpty()){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
