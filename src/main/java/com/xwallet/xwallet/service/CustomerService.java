package com.xwallet.xwallet.service;

import com.xwallet.xwallet.model.dto.CustomerDTO;
import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.repository.CustomerRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.xwallet.xwallet.utils.Constants.*;
import static com.xwallet.xwallet.utils.Validator.*;

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

    public Customer addCustomer(CustomerDTO customer) {
        if(!isValid(customer))
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_CUSTOMER);

        if(customerRepository.findCustomerByCustomerEmail(customer.getCustomerEmail()) != null)
            throw new RuntimeException(NOT_FOUND_CUSTOMER);

        return customerRepository.save(Customer.builder()
                        .customerName(customer.getCustomerName())
                        .customerEmail(customer.getCustomerEmail())
                        .customerPhone(customer.getCustomerPhone())
                        .customerAddress(customer.getCustomerAddress())
                        .customerCity(customer.getCustomerCity())
                        .build());
    }

    public Customer updateCustomer(Long customerId, CustomerDTO customer) {
        if(customer == null || customerId == null || isValid(customer))
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_CUSTOMER);

        Customer savedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CUSTOMER));
        savedCustomer.setCustomerName(customer.getCustomerName());
        savedCustomer.setCustomerPhone(customer.getCustomerPhone());
        savedCustomer.setCustomerEmail(customer.getCustomerEmail());
        savedCustomer.setCustomerAddress(customer.getCustomerAddress());
        savedCustomer.setCustomerCity(customer.getCustomerCity());

        return customerRepository.save(savedCustomer);
    }

    public ResponseEntity<HttpStatus> deleteCustomer(Long customerId) {
        if(customerId == null)
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_CUSTOMER);
        customerRepository.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
