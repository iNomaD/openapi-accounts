package com.devexperts.accounts.mappers.impl;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.entities.patch.AccountPatch;
import com.devexperts.accounts.mappers.AccountMapper;
import com.devexperts.accounts.model.AccountJson;
import com.devexperts.accounts.model.CreateAccountJson;
import com.devexperts.accounts.model.UpdateAccountJson;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountJson toDto(Account entity) {
        return new AccountJson()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .balance(entity.getBalance());
    }

    @Override
    public Account toEntity(CreateAccountJson dto) {
        return Account.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .balance(dto.getBalance())
            .build();
    }

    @Override
    public AccountPatch toPatch(UpdateAccountJson jsonPatch) {
        return AccountPatch.builder()
            .name(jsonPatch.getName())
            .email(jsonPatch.getEmail())
            .balance(jsonPatch.getBalance())
            .build();
    }
}
