package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvestmentDTO {
    private Long accountId;
    private Long customerId;
    private Integer investmentDays;
    private Double investmentAmount;
}
