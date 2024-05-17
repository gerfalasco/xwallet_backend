package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.dto.CustomerDTO;
import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.xwallet.xwallet.utils.Constants.TEST_MESSAGE;
import static com.xwallet.xwallet.utils.Constants.TEST_METHOD_NAME;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return TEST_MESSAGE.concat(CLASS_NAME).concat("." + TEST_METHOD_NAME);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody CustomerDTO customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }


}
