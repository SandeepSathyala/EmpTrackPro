package com.example.SpringBoot.Application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Schema(description = "Employee Data Transfer Object")
public class EmployeeDTO {

    @NotBlank(message = "Employee name is required")
    @Schema(description = "Name of the employee", example = "Sandeep Sathyala")
    private String empName;

    @NotBlank(message = "Role must not be empty")
    @Schema(description = "Role of the employee", example = "Java Developer")
    private String empRole;

    @Schema(description = "Salary of the employee", example = "50000.0")
    @NotNull(message = "Salary must not be null")
    private Double empSalary;

    @Schema(description = "Email address", example = "sandeep@gmail.com")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Schema(description = "Phone number", example = "9876543210")
    private Long phoneNumber;


    @Valid
    @Schema(description = "Employee's address")
    private AddressDTO address;

    public EmployeeDTO() {}

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
