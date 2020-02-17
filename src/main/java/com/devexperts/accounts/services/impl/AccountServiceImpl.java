package com.devexperts.accounts.services.impl;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.entities.patch.AccountPatch;
import com.devexperts.accounts.exceptions.AccountNotFoundException;
import com.devexperts.accounts.repositories.AccountRepository;
import com.devexperts.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findAccountById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateAccount(long id, AccountPatch accountPatch) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException(id));
        account = accountPatch.apply(account);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(long id) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException(id));
        accountRepository.delete(account);
    }
}
