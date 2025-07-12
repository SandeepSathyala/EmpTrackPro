package com.example.SpringBoot.Application.controller;

import com.example.SpringBoot.Application.dto.EmployeeDTO;
import com.example.SpringBoot.Application.model.Employee;
import com.example.SpringBoot.Application.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService empService;

    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @Operation(summary = "Create new employee", description = "Takes EmployeeDTO and saves into DB")
    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return empService.createEmployee(employeeDTO);
    }

    @Operation(summary = "Get all employees", description = "Returns list of all employees")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long empId) {
        return empService.getEmployeeById(empId);
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee(@Valid @PathVariable("id") Long empId, @RequestBody EmployeeDTO employeeDTO) {
        return empService.updateEmployee(empId, employeeDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteEmployee(@PathVariable("id") Long empId) {
        return empService.deleteEmployee(empId);
    }
}
