package com.xwallet.xwallet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_PHONE")
    private String customerPhone;

    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;

    @Column(name = "CUSTOMER_ADDRESS")
    private String customerAddress;

    @Column(name = "CUSTOMER_CITY")
    private String customerCity;

    @OneToMany(targetEntity = Account.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Account> accountList;
}
