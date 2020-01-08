package com.devexperts.accounts.services;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.entities.patch.AccountPatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Page<Account> findAllAccounts(Pageable pageable);

    Account addAccount(Account account);

    Optional<Account> findAccountById(long id);

    void updateAccount(long id, AccountPatch accountPatch);

    void deleteAccount(long id);
}
