package com.devexperts.accounts.api;

import com.devexperts.accounts.exceptions.AccountNotFoundException;
import com.devexperts.common.model.ErrorJson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AccountNotFoundException.class)
    protected ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException e, WebRequest request) {
        ErrorJson error = new ErrorJson()
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {
        ErrorJson error = new ErrorJson()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
