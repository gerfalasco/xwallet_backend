package com.xwallet.xwallet.service;

import com.xwallet.xwallet.model.dto.AccountDTO;
import com.xwallet.xwallet.model.entity.Account;
import com.xwallet.xwallet.model.entity.Customer;
import com.xwallet.xwallet.repository.AccountRepository;
import com.xwallet.xwallet.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.xwallet.xwallet.utils.Constants.*;
import static com.xwallet.xwallet.utils.Validator.isValidAccount;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll()
                .stream().map(account -> AccountDTO.builder()
                        .accountId(account.getCustomer().getCustomerId())
                        .customerId(account.getCustomer().getCustomerId())
                        .accountBalance(account.getAccountBalance())
                        .accountCurrency(account.getAccountCurrency())
                        .accountType(account.getAccountType()).build()).toList();
    }

    public AccountDTO getAccount(Long accountId) {
        Account account= accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ACCOUNT));

        return AccountDTO.builder()
                .accountId(accountId)
                .customerId(account.getCustomer().getCustomerId())
                .accountBalance(account.getAccountBalance())
                .accountCurrency(account.getAccountCurrency())
                .accountType(account.getAccountType()).build();
    }

    public Account addAccount(AccountDTO account) {
        if(!isValidAccount(account))
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_ACCOUNT);

        Customer customer = customerRepository.findById(account.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CUSTOMER));

        return accountRepository.save(Account.builder()
                        .customer(customer)
                        .accountCurrency(account.getAccountCurrency())
                        .accountType(account.getAccountType())
                        .accountBalance(account.getAccountBalance())
                        .build());
    }

    public Account updateAccount(Long accountId, AccountDTO account) {
        if(!isValidAccount(account))
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_ACCOUNT);

        Account savedAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ACCOUNT));

        Customer customer = customerRepository.findById(savedAccount.getCustomer().getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_CUSTOMER));

        savedAccount.setCustomer(customer);
        savedAccount.setAccountBalance(account.getAccountBalance());
        savedAccount.setAccountCurrency(account.getAccountCurrency());
        savedAccount.setAccountType(account.getAccountType());

        return accountRepository.save(savedAccount);
    }

    public ResponseEntity<HttpStatus> deleteAccount(Long accountId) {
        if(accountId == null)
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_ACCOUNT);

        accountRepository.deleteById(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
