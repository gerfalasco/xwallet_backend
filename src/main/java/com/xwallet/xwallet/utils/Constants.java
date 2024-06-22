package com.xwallet.xwallet.utils;

import software.amazon.awssdk.regions.Region;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TRANSACTION_EXTRACTION = "extraction";
    public static final String TRANSACTION_DEPOSIT = "deposit";
    public static final String MOVEMENT_TRANSFER = "transfer";
    public static final String MOVEMENT_EXCHANGE = "exchange";
    public static final String MOVEMENT_INVEST = "invest";
    public static final String TRANSACTION_DESCRIPTION = "Cash movement - ATM/Bank.";
    public static final String TRANSFER_TO_DESCRIPTION = "Transfer made to account ";
    public static final String TRANSFER_FROM_DESCRIPTION = "Transfer received from account ";
    public static final String EXCHANGE_TO_DESCRIPTION = "Exchanged money to ";
    public static final String EXCHANGE_FROM_DESCRIPTION = "Exchanged money from  ";
    public static final String INVESTMENT_START_DESCRIPTION = "Investment set up.";
    public static final String INVESTMENT_END_DESCRIPTION = "Investment return.";
    public static final String TEST_MESSAGE = "The following endpoint is responding: ";
    public static final String TEST_METHOD_NAME = "testEndpoint";
    public static final String NOT_FOUND_CUSTOMER = "Customer not found.";
    public static final String NOT_FOUND_ACCOUNT = "Account not found.";
    public static final String NOT_FOUND_ORIGIN_ACCOUNT = "Origin account not found.";
    public static final String NOT_FOUND_DESTINATION_ACCOUNT = "Destination account not found.";
    public static final String ILLEGAL_ARGUMENTS_CUSTOMER = "Customer information is not complete.";
    public static final String ILLEGAL_ARGUMENTS_ACCOUNT = "Account information is not complete.";
    public static final String ILLEGAL_ARGUMENTS_INSUFFICIENT = "Not enough balance in the account.";
    public static final String ALREADY_EXISTS_CUSTOMER = "Customer already exists.";
    public static final String FORBIDDEN_ACTION = "This action cannot be completed";
    public static final String FORBIDDEN_TRANSFER = "Origin and destination account are not in the same currency.";
    public static final String AMAZON_SQS_URL = "https://sqs.us-east-1.amazonaws.com/232171128702/ColaCore";
    public static final String AMAZON_SNS_ARN = "arn:aws:sns:us-east-1:232171128702:CoreSNSTopic";
    public static final Region REGION = Region.US_EAST_1;

}
