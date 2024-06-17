package com.xwallet.xwallet.repository;

import com.xwallet.xwallet.model.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findInvestmentsByInvestmentEndDate(Date endDate);
}
