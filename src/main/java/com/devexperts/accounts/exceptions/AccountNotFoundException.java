package com.devexperts.accounts.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(long accountId) {
        super("Account not found: " + accountId);
    }
}
