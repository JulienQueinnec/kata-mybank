package com.oxiane.kata.mybank.repository;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAllByAccount(Account account);
}
