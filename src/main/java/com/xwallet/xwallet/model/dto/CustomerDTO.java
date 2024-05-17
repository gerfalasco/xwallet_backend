package com.xwallet.xwallet.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDTO {
    private Long customerId;
    private List<AccountDTO> accounts;
}
