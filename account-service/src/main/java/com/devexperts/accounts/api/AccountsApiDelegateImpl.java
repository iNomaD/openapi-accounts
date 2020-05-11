package com.devexperts.accounts.api;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.exceptions.AccountNotFoundException;
import com.devexperts.accounts.mappers.AccountMapper;
import com.devexperts.accounts.model.AccountJson;
import com.devexperts.accounts.model.CreateAccountJson;
import com.devexperts.accounts.model.UpdateAccountJson;
import com.devexperts.accounts.services.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountsApiDelegateImpl implements AccountsApiDelegate {

    private final AccountServiceImpl accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountsApiDelegateImpl(AccountServiceImpl accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
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
    public ResponseEntity<AccountJson> addAccount(CreateAccountJson createAccountJson) {
        Account account = accountService.addAccount(accountMapper.toEntity(createAccountJson));
        return new ResponseEntity<>(accountMapper.toDto(account), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountJson> findAccountById(Long accountId) {
        Account account = accountService.findAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        return new ResponseEntity<>(accountMapper.toDto(account), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateAccount(Long accountId, UpdateAccountJson updateAccountJson) {
        accountService.updateAccount(accountId, accountMapper.toPatch(updateAccountJson));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAccount(Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
