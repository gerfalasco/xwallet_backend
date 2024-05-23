package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    private Long accountId;
    private Long customerId;
    private String transactionType;
    private Double amount;
}
