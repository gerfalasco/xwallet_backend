package com.xwallet.xwallet.utils;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String TEST_MESSAGE = "The following endpoint is responding: ";
    public static final String TEST_METHOD_NAME = "testEndpoint";
    public static final String NOT_FOUND_CUSTOMER = "Customer not found.";
    public static final String NOT_FOUND_ACCOUNT = "Account not found.";
    public static final String ILLEGAL_ARGUMENTS_CUSTOMER = "Customer information is not complete.";
    public static final String ILLEGAL_ARGUMENTS_ACCOUNT = "Account information is not complete.";
    public static final String ALREADY_EXISTS_CUSTOMER = "Customer already exists.";
}
