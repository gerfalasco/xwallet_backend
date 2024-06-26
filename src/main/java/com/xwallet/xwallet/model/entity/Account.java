package com.xwallet.xwallet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Description;
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
@Table(name = "accounts", schema = "public", catalog = "xcore")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "account_balance", precision = 2)
    private Double accountBalance;

    @Column(name = "account_type", length = 10)
    private String accountType;

    @Column(name = "account_currency", length = 5)
    private String accountCurrency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(targetEntity = Movement.class, fetch = FetchType.LAZY, mappedBy = "account")
    @Description(value = "List of movements made from and to the account.")
    private List<Movement> movementListList;
}
