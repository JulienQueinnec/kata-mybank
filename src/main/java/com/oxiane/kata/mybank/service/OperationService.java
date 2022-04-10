package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;

public interface OperationService {
    void operateDeposit(Account account, Double amount);

    void operateWithdrawal(Account account, Double amount);
}
