package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if(optional.isPresent()) {
            employee = optional.get();
        }else{
            throw new RuntimeException("Employee not found by id ::" +id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
//        this.employeeRepository.deleteById(id);
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Student with id " + id + " does not exist"
            );
        }
        employeeRepository.deleteById(id);
    }
}
