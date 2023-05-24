package com.restspi.SpringBoot.RestAPI.controller;

import com.restspi.SpringBoot.RestAPI.model.Employee;
import com.restspi.SpringBoot.RestAPI.model.Response;
import com.restspi.SpringBoot.RestAPI.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/saveEmployee")
    public ResponseEntity<Response> saveEmployeeDetails(@RequestBody Employee employee){
        employeeService.saveEmployeeDetails(employee);
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Employee Details Saved Successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("isSaved","true")
                .body(response);

    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployeeDetails();
    }

    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@PathVariable long id){
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Response> deleteEmpById(@RequestParam(name = "id") long id){
        Response response = new Response();
        if(employeeService.deleteEmpById(id)){
            response.setStatusCode("200");
            response.setStatusMsg("Employee Details Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK)
                    .header("isDeleted","true")
                    .body(response);
        }

        response.setStatusCode("404");
        response.setStatusMsg("Employee Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("isDeleted","false")
                .body(response);

    }

    @PutMapping("/updateEmp/{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@RequestBody Employee employee,
                                                          @PathVariable long id){
        Employee emp = employeeService.findEmployeeById(id);
        if(emp!=null){
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setEmail(employee.getEmail());
            employeeService.saveEmployeeDetails(emp);
            return new ResponseEntity<Employee>(emp,HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(new Employee(),HttpStatus.BAD_REQUEST);
    }
}
