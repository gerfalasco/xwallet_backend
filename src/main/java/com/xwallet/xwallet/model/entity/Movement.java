package com.xwallet.xwallet.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "movements", schema = "public", catalog = "xcore")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", nullable = false)
    private long movementId;

    @Column(name = "movement_type", length = 30)
    private String movementType;

    @Column(name = "movement_amount", precision = 0)
    private Double movementAmount;

    @Column(name = "movement_description", length = 50)
    private String movementDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;
}
