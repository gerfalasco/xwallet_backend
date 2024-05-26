package com.xwallet.xwallet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "investments", schema = "public", catalog = "xcore")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investment_id", nullable = false)
    private long investmentId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "investment_days")
    private Integer investmentDays;

    @Column(name = "investment_start_date")
    private Date investmentStartDate;

    @Column(name = "investment_end_date")
    private Date investmentEndDate;

    @Column(name = "investment_amount", precision = 0)
    private Double investmentAmount;

    @Column(name = "investment_rate", precision = 0)
    private Double investmentRate;
}