package com.devexperts.accounts.api;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.mappers.AccountMapper;
import com.devexperts.accounts.model.AccountJson;
import com.devexperts.accounts.model.CreateAccountJson;
import com.devexperts.accounts.model.UpdateAccountJson;
import com.devexperts.accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountsApiDelegateImpl implements AccountsApiDelegate {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsApiDelegateImpl(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseEntity<AccountJson> addAccount(CreateAccountJson createAccountJson) {
        Account account = accountService.addAccount(accountMapper.toEntity(createAccountJson));
        return new ResponseEntity<>(accountMapper.toDto(account), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Long accountId) {
        return null;
    }

    @Override
    public ResponseEntity<AccountJson> findAccountById(Long accountId) {
        Optional<Account> account = accountService.findAccountById(accountId);
        if (account.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountMapper.toDto(account.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AccountJson>> findAllAccounts(Integer page, Integer limit) {
        List<AccountJson> result = accountService.findAllAccounts(PageRequest.of(page, limit))
            .getContent()
            .stream()
            .map(accountMapper::toDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAccount(Long accountId, UpdateAccountJson updateAccountJson) {
        return null;
    }
}
