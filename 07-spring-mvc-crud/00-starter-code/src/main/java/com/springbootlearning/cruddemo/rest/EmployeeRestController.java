package com.springbootlearning.cruddemo.rest;

import com.springbootlearning.cruddemo.entity.Employee;
import com.springbootlearning.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    private ObjectMapper mapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper mapper){
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        Employee result = employeeService.findById(id);

        if(result == null){
            throw new RuntimeException("Could not find employee w/id of " + id);
        }

        return result;
    }

    // new, not update
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        // set id to 0 to force save of new item
        employee.setId(0);
        if(employee.getFirstName() == "" || employee.getLastName() == "" || employee.getEmail() == "") {
            throw new RuntimeException("Missing one or more required parameters, please correctly fill in");
        }
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> paylod){
        Employee toUpdate = employeeService.findById(id);

        if(toUpdate == null){
            throw new RuntimeException("Could not find employee w/id of " + id);
        }
        if(paylod.containsKey("id")){
            throw new RuntimeException("Request body cannot contain id");
        }

        return employeeService.save(apply(paylod, toUpdate));
    }

    private Employee apply(Map<String, Object> paylod, Employee employee){
        ObjectNode employeeNode = mapper.convertValue(employee, ObjectNode.class);
        ObjectNode payloadNode = mapper.convertValue(paylod, ObjectNode.class);

        employeeNode.setAll(payloadNode);
        return mapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee result = employeeService.findById(id);

        if(result == null){
            throw new RuntimeException("Could not find employee w/id of " + id);
        }

        employeeService.deleteById(id);
        return "Delete employee w/id of " + id;
    }
}
