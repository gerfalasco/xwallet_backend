package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.model.dto.TransactionDTO;
import com.xwallet.xwallet.model.dto.TransferDTO;
import com.xwallet.xwallet.model.entity.Account;
import com.xwallet.xwallet.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.xwallet.xwallet.utils.Constants.TEST_MESSAGE;
import static com.xwallet.xwallet.utils.Constants.TEST_METHOD_NAME;

@RestController
@Tag(name = "operations", description = "Endpoints related to manage operations.")
@CrossOrigin(originPatterns = "*")
@RequestMapping(value = "/api/v1/operations")
public class OperationController {

    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping("/test")
    @Operation(hidden = true)
    public String testEndpoint() {
        return TEST_MESSAGE.concat(CLASS_NAME).concat("." + TEST_METHOD_NAME);
    }

    @Operation(summary = "Makes a cash movement impacting only one account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public Account modifyBalance(@RequestBody TransactionDTO transaction){
        return operationService.modifyBalance(transaction);
    }

    @Operation(summary = "Moves money between two accounts, given they use the same currency.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.OK)
    public Account transferBalance(@RequestBody TransferDTO transfer){
        return operationService.transferBalance(transfer);
    }

    @Operation(summary = "Exchange money currency between two accounts, given they belong to the same customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successful Response",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden Access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Account Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server Error", content = @Content)
    })
    @PostMapping("/exchanges")
    @ResponseStatus(HttpStatus.OK)
    public Account exchangeBalance(@RequestBody TransferDTO exchange){
        return operationService.exchangeBalance(exchange);
    }

}
