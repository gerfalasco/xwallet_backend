package com.xwallet.xwallet.utils;

import com.xwallet.xwallet.model.dto.AccountDTO;
import com.xwallet.xwallet.model.dto.CustomerDTO;
import org.springframework.util.StringUtils;

public class Validator {

    private Validator(){
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidCustomer(CustomerDTO customer){
        return (customer != null &&
                StringUtils.hasText(customer.getCustomerName()) &&
                (StringUtils.hasText(customer.getCustomerPhone()) ||
                StringUtils.hasText(customer.getCustomerEmail())) &&
                StringUtils.hasText(customer.getCustomerAddress()));
    }

    public static boolean isValidAccount(AccountDTO account){
        return (account != null &&
                StringUtils.hasText(account.getAccountType()) &&
                StringUtils.hasText(account.getAccountCurrency()) &&
                account.getAccountBalance()>=0);
    }
}
