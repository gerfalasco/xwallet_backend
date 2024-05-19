package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private Double accountBalance;
    private String accountType;
    private String accountCurrency;
}
