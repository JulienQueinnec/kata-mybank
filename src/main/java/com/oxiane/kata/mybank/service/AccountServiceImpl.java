package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.BankUser;
import com.oxiane.kata.mybank.exception.UnidentifiedUserException;
import com.oxiane.kata.mybank.repository.AccountRepository;
import com.oxiane.kata.mybank.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccount(String accountNumber, String userConnectedName) throws UnidentifiedUserException {
        Optional<BankUser> user = userRepository.getUserByName(userConnectedName);
        if (user.isEmpty()) {
            LOGGER.error("Unknow user");
            throw new UnidentifiedUserException();
        }
        return accountRepository.findAccountByAccountNumberAndBankUser(accountNumber, user.get());
    }
}
