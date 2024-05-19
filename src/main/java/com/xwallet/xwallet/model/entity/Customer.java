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
@Table(name = "customers", schema = "public", catalog = "dbxwallet")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_name", length = 50)
    private String customerName;

    @Column(name = "customer_phone", length = 20)
    private String customerPhone;

    @Column(name = "customer_email", length = 50)
    private String customerEmail;

    @Column(name = "customer_address", length = 100)
    private String customerAddress;

    @Column(name = "customer_city", length = 50)
    private String customerCity;

    @OneToMany(targetEntity = Account.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Account> accountList;
}
