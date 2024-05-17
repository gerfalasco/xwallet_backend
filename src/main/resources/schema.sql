CREATE TABLE customers (
    customer_Id BIGINT NOT NULL PRIMARY KEY,
    customer_Name VARCHAR(50),
    customer_Phone VARCHAR(20),
    customer_Email VARCHAR(50),
    customer_Address VARCHAR(100),
    customer_City VARCHAR(50)
);

CREATE TABLE accounts (
    account_Id BIGINT NOT NULL PRIMARY KEY,
    customer_Id BIGINT NOT NULL,
    account_Balance DOUBLE PRECISION,
    account_Type VARCHAR(10),
    account_Currency VARCHAR(5),
    FOREIGN KEY (customer_Id) REFERENCES customers
);