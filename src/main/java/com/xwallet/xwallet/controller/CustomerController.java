package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.dto.CustomerDTO;
import com.xwallet.xwallet.model.dto.CustomersDTO;
import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.xwallet.xwallet.utils.Constants.TEST_MESSAGE;
import static com.xwallet.xwallet.utils.Constants.TEST_METHOD_NAME;

@RestController
@Tag(name = "customers", description = "Endpoints related to manage customer information.")
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    @Operation(hidden = true)
    public String testEndpoint() {
        return TEST_MESSAGE.concat(CLASS_NAME).concat("." + TEST_METHOD_NAME);
    }

    @Operation(summary = "Gets a list of all Customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                         description = "Successful Response",
                         content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomersDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @Operation(summary = "Gets details of one Customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @Operation(summary = "Adds a new Customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody CustomerDTO customer) {
        return customerService.addCustomer(customer);
    }

    @Operation(summary = "Updates one Customer information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerDTO customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @Operation(summary = "Deletes one Customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema()) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
