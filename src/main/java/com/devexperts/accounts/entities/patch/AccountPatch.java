package com.devexperts.accounts.entities.patch;

import com.devexperts.accounts.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPatch {

    private JsonNullable<String> name;
    private JsonNullable<String> email;
    private JsonNullable<BigDecimal> balance;

    public Account apply(Account account) {
        return Account.builder()
            .id(account.getId())
            .name(name.orElse(account.getName()))
            .email(email.orElse(account.getEmail()))
            .balance(balance.orElse(account.getBalance()))
            .build();
    }
}
