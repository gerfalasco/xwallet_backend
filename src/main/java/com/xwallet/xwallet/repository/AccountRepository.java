package com.xwallet.xwallet.repository;

import com.xwallet.xwallet.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
