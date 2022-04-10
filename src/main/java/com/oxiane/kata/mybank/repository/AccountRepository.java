package com.oxiane.kata.mybank.repository;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.BankUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Optional<Account> findAccountByAccountNumberAndBankUser(String accountNumber, BankUser user);
}
