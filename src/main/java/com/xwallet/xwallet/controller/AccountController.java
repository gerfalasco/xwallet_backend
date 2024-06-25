package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.dto.AccountDTO;
import com.xwallet.xwallet.model.dto.AccountsDTO;
import com.xwallet.xwallet.model.entity.Account;
import com.xwallet.xwallet.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.xwallet.xwallet.utils.Constants.TEST_MESSAGE;
import static com.xwallet.xwallet.utils.Constants.TEST_METHOD_NAME;

@RestController
@Tag(name = "accounts", description = "Endpoints related to manage account information.")
@CrossOrigin(originPatterns = "*")
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/test")
    @Operation(hidden = true)
    public String testEndpoint() {
        return TEST_MESSAGE.concat(CLASS_NAME).concat("." + TEST_METHOD_NAME);
    }

    @Operation(summary = "Gets a list of all the Accounts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AccountsDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @Operation(summary = "Gets details of one Account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @GetMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable("accountId") Long accountId) {
        return accountService.getAccount(accountId);
    }

    @Operation(summary = "Adds one new Account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody AccountDTO account) {
        return accountService.addAccount(account);
    }

    @Operation(summary = "Updates details of one Account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PutMapping("/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountDTO account) {
        return accountService.updateAccount(accountId, account);
    }

    @Operation(summary = "Deletes one Account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @DeleteMapping("/{accountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("accountId") Long accountId) {
        return accountService.deleteAccount(accountId);
    }
}
