package com.xwallet.xwallet.service;

import com.xwallet.xwallet.model.dto.TransactionDTO;
import com.xwallet.xwallet.model.dto.TransferDTO;
import com.xwallet.xwallet.model.entity.Account;
import com.xwallet.xwallet.model.entity.Movement;
import com.xwallet.xwallet.repository.AccountRepository;
import com.xwallet.xwallet.repository.CustomerRepository;
import com.xwallet.xwallet.repository.ExchangeRepository;
import com.xwallet.xwallet.repository.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;

import static com.xwallet.xwallet.utils.Constants.*;

@Service
public class OperationService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final MovementRepository movementRepository;
    private final ExchangeRepository exchangeRepository;

    public OperationService(AccountRepository accountRepository, CustomerRepository customerRepository, MovementRepository movementRepository, ExchangeRepository exchangeRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.movementRepository = movementRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Transactional
    public Account modifyBalance(TransactionDTO transaction) {
        Account account = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ACCOUNT));

        if(!Objects.equals(account.getCustomer().getCustomerId(), transaction.getCustomerId()))
            throw new RuntimeException(FORBIDDEN_ACTION);

        if(transaction.getTransactionType().equals(TRANSACTION_EXTRACTION) && account.getAccountBalance() < transaction.getAmount())
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_INSUFFICIENT);

        movementRepository.save(Movement.builder()
                        .account(account)
                        .movementType(transaction.getTransactionType())
                        .movementAmount(transaction.getAmount() * (transaction.getTransactionType().equals(TRANSACTION_EXTRACTION) ? -1 : 1))
                        .movementDescription(TRANSACTION_DESCRIPTION)
                        .build());

        Double newBalance = account.getAccountBalance()
                + transaction.getAmount() * (transaction.getTransactionType().equals(TRANSACTION_EXTRACTION) ? -1 : 1);
        account.setAccountBalance(newBalance);
        return accountRepository.save(account);
    }

    @Transactional
    public Account transferBalance(TransferDTO transaction) {

        Account originAccount = accountRepository.findById(transaction.getOriginAccountId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ORIGIN_ACCOUNT));

        Account destinationAccount = accountRepository.findById(transaction.getDestinationAccountId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_DESTINATION_ACCOUNT));

        if(!Objects.equals(transaction.getOriginCustomerId(), originAccount.getCustomer().getCustomerId()))
            throw new IllegalArgumentException(FORBIDDEN_ACTION);

        if(!Objects.equals(originAccount.getAccountCurrency(), destinationAccount.getAccountCurrency()))
            throw new IllegalArgumentException(FORBIDDEN_TRANSFER);

        if(transaction.getAmount() > originAccount.getAccountBalance())
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_INSUFFICIENT);

        movementRepository.save(Movement.builder()
                        .account(originAccount)
                        .movementType(MOVEMENT_TRANSFER)
                        .movementAmount((-1) * transaction.getAmount())
                        .movementDescription(TRANSFER_TO_DESCRIPTION.concat(destinationAccount.getAccountId().toString()))
                        .build());

        movementRepository.save(Movement.builder()
                .account(destinationAccount)
                .movementType(MOVEMENT_TRANSFER)
                .movementAmount(transaction.getAmount())
                .movementDescription(TRANSFER_FROM_DESCRIPTION.concat(originAccount.getAccountId().toString()))
                .build());

        destinationAccount.setAccountBalance(transaction.getAmount());
        accountRepository.save(destinationAccount);

        originAccount.setAccountBalance((-1) * transaction.getAmount());
        return accountRepository.save(originAccount);
    }

    public Account exchangeBalance(TransferDTO exchange) {
        Account originAccount = accountRepository.findById(exchange.getOriginAccountId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_ORIGIN_ACCOUNT));

        Account destinationAccount = accountRepository.findById(exchange.getDestinationAccountId())
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_DESTINATION_ACCOUNT));

        if(originAccount.getCustomer() != destinationAccount.getCustomer() ||
                !Objects.equals(originAccount.getCustomer().getCustomerId(), exchange.getOriginCustomerId()))
            throw new IllegalArgumentException(FORBIDDEN_ACTION);

        if(exchange.getAmount() > originAccount.getAccountBalance())
            throw new IllegalArgumentException(ILLEGAL_ARGUMENTS_INSUFFICIENT);

        double exchangeRate = exchangeRepository.findExchangeByOriginAndDestination(originAccount.getAccountCurrency(), destinationAccount.getAccountCurrency()).getRate();

        double newOriginBalance = originAccount.getAccountBalance() - exchange.getAmount();
        movementRepository.save(Movement.builder()
                .account(originAccount)
                .movementType(MOVEMENT_EXCHANGE)
                .movementAmount((-1) * exchange.getAmount())
                .movementDescription(EXCHANGE_TO_DESCRIPTION.concat(destinationAccount.getAccountCurrency()))
                .build());

        double newDestinationBalance = destinationAccount.getAccountBalance() + exchange.getAmount() * exchangeRate;
        movementRepository.save(Movement.builder()
                .account(destinationAccount)
                .movementType(MOVEMENT_EXCHANGE)
                .movementAmount(exchange.getAmount() * exchangeRate)
                .movementDescription(EXCHANGE_FROM_DESCRIPTION.concat(originAccount.getAccountCurrency()))
                .build());

        destinationAccount.setAccountBalance(newDestinationBalance);
        accountRepository.save(destinationAccount);

        originAccount.setAccountBalance(newOriginBalance);
        return accountRepository.save(originAccount);
    }
}
