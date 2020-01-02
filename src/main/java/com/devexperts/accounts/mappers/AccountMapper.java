package com.devexperts.accounts.mappers;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.entities.patch.AccountPatch;
import com.devexperts.accounts.model.AccountJson;
import com.devexperts.accounts.model.CreateAccountJson;
import com.devexperts.accounts.model.UpdateAccountJson;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountJson toDto(Account entity) {
        return new AccountJson()
            .id(entity.getId())
            .name(entity.getName())
            .email(entity.getEmail())
            .balance(entity.getBalance());
    }

    public Account toEntity(CreateAccountJson dto) {
        return Account.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .balance(dto.getBalance())
            .build();
    }

    public AccountPatch toPatch(UpdateAccountJson jsonPatch) {
        return null;
    }
}
