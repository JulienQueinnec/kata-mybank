package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.Operation;

import java.util.List;

public interface OperationService {
    void operateDeposit(Account account, Double amount);

    void operateWithdrawal(Account account, Double amount);

    List<Operation> listOperations(Account account);
}
