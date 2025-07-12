package com.example.SpringBoot.Application.service;

import com.example.SpringBoot.Application.dto.AddressDTO;
import com.example.SpringBoot.Application.dto.EmployeeDTO;
import com.example.SpringBoot.Application.exceptions.ResourceNotFoundException;
import com.example.SpringBoot.Application.model.Address;
import com.example.SpringBoot.Application.model.Employee;
import com.example.SpringBoot.Application.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee createEmployee(EmployeeDTO dto) {
        Employee emp = mapDtoToEntity(dto);
        return employeeRepo.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(long EmpId){
        return employeeRepo.findById(EmpId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + EmpId + " not found"));
    }


    public Employee updateEmployee(Long empId, EmployeeDTO dto) {
        Employee emp = employeeRepo.findById(empId).orElse(null);
        if (emp == null) return null;

        if (dto.getEmpName() != null) emp.setEmpName(dto.getEmpName());
        if (dto.getEmail() != null) emp.setEmail(dto.getEmail());
        if (dto.getEmpRole() != null) emp.setEmpRole(dto.getEmpRole());
        if (dto.getEmpSalary() != null) emp.setEmpSalary(dto.getEmpSalary());
        if (dto.getPhoneNumber() != null) emp.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAddress() != null) {
            AddressDTO addrDto = dto.getAddress();
            Address addr = emp.getAddress() == null ? new Address() : emp.getAddress();

            if (addrDto.getState() != null) addr.setState(addrDto.getState());
            if (addrDto.getArea() != null) addr.setArea(addrDto.getArea());
            if (addrDto.getDistrict() != null) addr.setDistrict(addrDto.getDistrict());
            if (addrDto.getPincode() != null) addr.setPincode(addrDto.getPincode());

            emp.setAddress(addr);
        }

        return employeeRepo.save(emp);
    }

    public String deleteEmployee(Long empId) {
        employeeRepo.deleteById(empId);
        return "Deleted Successfully";
    }

    private Employee mapDtoToEntity(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setEmpName(dto.getEmpName());
        emp.setEmail(dto.getEmail());
        emp.setEmpRole(dto.getEmpRole());
        emp.setEmpSalary(dto.getEmpSalary());
        emp.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAddress() != null) {
            Address addr = new Address();
            addr.setState(dto.getAddress().getState());
            addr.setArea(dto.getAddress().getArea());
            addr.setDistrict(dto.getAddress().getDistrict());
            addr.setPincode(dto.getAddress().getPincode());
            emp.setAddress(addr);
        }

        return emp;
    }
}
