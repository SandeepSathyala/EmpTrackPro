package com.example.SpringBoot.Application.controller;

import com.example.SpringBoot.Application.dto.EmployeeDTO;
import com.example.SpringBoot.Application.exceptions.ApiErrorResponse;
import com.example.SpringBoot.Application.model.Employee;
import com.example.SpringBoot.Application.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Operations related to Employees")
public class EmployeeController {

    private final EmployeeService empService;

    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @Operation(
            summary = "Create a new employee",
            description = "Takes an employee object and stores it in the DB",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employee created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/create")
    public Employee createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return empService.createEmployee(employeeDTO);
    }

    @Operation(summary = "Get all employees", description = "Retrieves all employees from the database")
    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    @Operation(summary = "Get employee by ID", description = "Fetch employee by unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@Parameter(description = "ID of the employee to retrieve") @PathVariable("id")  Long empId) {
        return empService.getEmployeeById(empId);
    }

    @Operation(summary = "Update employee", description = "Update employee fields by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployee( @Parameter(description = "ID of the employee to update") @PathVariable("id") Long empId,
                                     @Valid @RequestBody EmployeeDTO employeeDTO) {
        return empService.updateEmployee(empId, employeeDTO);
    }

    @Operation(summary = "Delete employee", description = "Delete employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/deleteById/{id}")
    public String deleteEmployee(
            @Parameter(description = "ID of the employee to delete") @PathVariable("id") Long empId) {
        return empService.deleteEmployee(empId);
    }
}
