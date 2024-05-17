package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.service.CustomerService;
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

    @GetMapping("/{clientId}")
    public Customer getCustomer(@PathVariable("clientId") Long clientId) {
        return customerService.getCustomer(clientId);
    }

    @PostMapping("/{clientId}")
    public Customer addCustomer(@PathVariable("clientId") Long clientId) {
        return customerService.addCustomer(clientId);
    }

    @PutMapping("/{clientId}")
    public Customer updateCustomer(@PathVariable("clientId") Long clientId) {
        return customerService.updateCustomer(clientId);
    }

    @DeleteMapping("/{clientId}")
    public Void deleteCustomer(@PathVariable("clientId") Long clientId) {
        return customerService.deleteCustomer(clientId);
    }


}
