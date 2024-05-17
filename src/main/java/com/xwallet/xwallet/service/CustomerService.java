package com.xwallet.xwallet.service;

import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.xwallet.xwallet.utils.Constants.*;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers () {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long clientId) {
        return customerRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CUSTOMER));
    }

    public Customer addCustomer(Long clientId) {
        return null;
    }

    public Customer updateCustomer(Long clientId) {
        return null;
    }

    public Void deleteCustomer(Long clientId) {
        return null;
    }
}
