package com.devexperts.accounts.services;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Optional<Account> findAccountById(long id) {
        return accountRepository.findById(id);
    }

    public Page<Account> findAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
