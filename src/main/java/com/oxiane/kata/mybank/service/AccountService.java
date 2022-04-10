package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.exception.UnidentifiedUserException;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccount(String accountNumber, String userConnectedName) throws UnidentifiedUserException;
}
