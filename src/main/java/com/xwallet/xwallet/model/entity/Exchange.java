package com.xwallet.xwallet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "exchange", schema = "public", catalog = "xcore")
@IdClass(com.xwallet.xwallet.model.entity.ExchangePK.class)
public class Exchange {
    @Id
    @Column(name = "origin", nullable = false, length = 10)
    private String origin;

    @Id
    @Column(name = "destination", nullable = false, length = 10)
    private String destination;

    @Column(name = "rate", nullable = true, precision = 0)
    private Double rate;
}
