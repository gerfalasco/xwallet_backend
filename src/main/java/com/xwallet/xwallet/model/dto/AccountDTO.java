package com.xwallet.xwallet.model.dto;

import com.xwallet.xwallet.model.entity.Movement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private Double accountBalance;
    private String accountType;
    private String accountCurrency;
    private List<Movement> accountMovements;
}
