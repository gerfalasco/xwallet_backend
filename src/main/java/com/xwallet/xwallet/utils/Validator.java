package com.xwallet.xwallet.utils;

import com.xwallet.xwallet.model.dto.CustomerDTO;
import org.springframework.util.StringUtils;

public class Validator {

    public static boolean isValid(CustomerDTO customer){
        return (customer != null &&
                !StringUtils.hasText(customer.getCustomerName()) &&
                (!StringUtils.hasText(customer.getCustomerPhone()) ||
                !StringUtils.hasText(customer.getCustomerEmail())) &&
                !StringUtils.hasText(customer.getCustomerAddress()));
    }
}
