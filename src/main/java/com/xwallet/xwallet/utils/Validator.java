package com.xwallet.xwallet.utils;

import com.xwallet.xwallet.model.dto.AccountDTO;
import com.xwallet.xwallet.model.dto.CustomerDTO;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$",
            Pattern.CASE_INSENSITIVE
    );

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

    public static boolean isValidUUID(String uuidStr) {
        return UUID_PATTERN.matcher(uuidStr).matches();
    }
}
