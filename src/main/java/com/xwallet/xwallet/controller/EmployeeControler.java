package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.dto.CredentialsDTO;
import com.xwallet.xwallet.model.dto.EmployeeDTO;
import com.xwallet.xwallet.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.xwallet.xwallet.utils.Constants.TEST_MESSAGE;
import static com.xwallet.xwallet.utils.Constants.TEST_METHOD_NAME;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeControler {

    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final EmployeeService employeeService;

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/test")
    @Operation(hidden = true)
    public String testEndpoint() {
        return TEST_MESSAGE.concat(CLASS_NAME).concat("." + TEST_METHOD_NAME);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@RequestBody CredentialsDTO credentials) {
        return employeeService.addEmployee(credentials);
    }

    @PostMapping("/logins")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO loginEmployee(@RequestBody CredentialsDTO credentials) {
        return employeeService.loginEmployee(credentials);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDTO changePassword(@RequestBody CredentialsDTO credentials) {
        return employeeService.changePassword(credentials);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@RequestBody CredentialsDTO credentials) {
        employeeService.deleteEmployee(credentials);
    }
}
