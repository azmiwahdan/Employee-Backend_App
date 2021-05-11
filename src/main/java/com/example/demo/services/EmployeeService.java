package com.example.demo.services;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> new UserNotFoundException("Employee Not found "));

        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setImageUrl(employee.getImageUrl());
        return existingEmployee;
    }

    public void deleteEmployee(Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Employee Not found "));
         employeeRepository.deleteById(id);
    }


}
