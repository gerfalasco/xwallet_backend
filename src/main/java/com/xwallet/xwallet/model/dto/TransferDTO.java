package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDTO {
    private Long originAccountId;
    private Long destinationAccountId;
    private Long originCustomerId;
    private String transactionType;
    private Double amount;
}
