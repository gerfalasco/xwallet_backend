package com.xwallet.xwallet.service;

import com.xwallet.xwallet.model.dto.CredentialsDTO;
import com.xwallet.xwallet.model.dto.EmployeeDTO;
import com.xwallet.xwallet.model.entity.Employee;
import com.xwallet.xwallet.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

import static com.xwallet.xwallet.utils.Constants.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public EmployeeDTO addEmployee(CredentialsDTO credentials) {
        if(employeeRepository.findById(credentials.getEmail()).isPresent())
            throw new IllegalArgumentException(ALREADY_EXIST_EMPLOYEE);

        String password = StringUtils.hasText(credentials.getPassword())? credentials.getPassword() : DEFAULT_PASSWORD;
        return employeeRepository.save(Employee.builder()
                        .email(credentials.getEmail())
                        .password(password)
                        .build())
                .toDTO();
    }

    @Transactional(readOnly = true)
    public EmployeeDTO loginEmployee(CredentialsDTO credentials) {
        Employee employee = employeeRepository.findById(credentials.getEmail())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_EMPLOYEE));

        if(!employee.getPassword().equals(credentials.getPassword()))
            throw new IllegalArgumentException(FORBIDDEN_ACTION);

        return employee.toDTO();
    }

    public EmployeeDTO changePassword(CredentialsDTO credentials) {
        Employee employee = employeeRepository.findById(credentials.getEmail())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_EMPLOYEE));

        if(!employee.getPassword().equals(""))
            employee.setPassword(credentials.getPassword());

        return employeeRepository.save(employee).toDTO();
    }


    public void deleteEmployee(CredentialsDTO credentials) {
        employeeRepository.deleteById(credentials.getEmail());
    }
}
