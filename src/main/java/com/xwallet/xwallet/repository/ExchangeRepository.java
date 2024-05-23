package com.xwallet.xwallet.repository;

import com.xwallet.xwallet.model.entity.Exchange;
import com.xwallet.xwallet.model.entity.ExchangePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, ExchangePK> {
    Exchange findExchangeByOriginAndDestination(String origin, String destination);
}
