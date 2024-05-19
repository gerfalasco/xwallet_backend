package com.xwallet.xwallet.model.dto;

import com.xwallet.xwallet.model.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountsDTO {
    private List<Account> accounts;
}
