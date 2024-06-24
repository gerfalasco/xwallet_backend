package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferDTO {
    private String originAccountId;
    private String destinationAccountId;
    private String originCustomerId;
    private String transactionType;
    private Double amount;
}
