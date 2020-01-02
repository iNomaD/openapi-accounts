package com.devexperts.accounts.repositories;


import com.devexperts.accounts.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
