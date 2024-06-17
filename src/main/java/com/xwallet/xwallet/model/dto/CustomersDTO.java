package com.xwallet.xwallet.model.dto;

import com.xwallet.xwallet.model.entity.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomersDTO {
    private List<Customer> customers;
}
