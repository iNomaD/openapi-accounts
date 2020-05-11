package com.devexperts.accounts.mappers;

import com.devexperts.accounts.entities.Account;
import com.devexperts.accounts.entities.patch.AccountPatch;
import com.devexperts.accounts.model.AccountJson;
import com.devexperts.accounts.model.CreateAccountJson;
import com.devexperts.accounts.model.UpdateAccountJson;

public interface AccountMapper {

    AccountJson toDto(Account entity);

    Account toEntity(CreateAccountJson dto);

    AccountPatch toPatch(UpdateAccountJson jsonPatch);

}
