package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.BankOperationType;
import com.oxiane.kata.mybank.domain.Operation;
import com.oxiane.kata.mybank.repository.OperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void operateDeposit(Account account, Double amount) {
        Operation operation = new Operation(amount, account, BankOperationType.DEPOSIT);
        LOGGER.info("Save a deposit : {}", operation);
        operationRepository.save(operation);
    }

    @Override
    public void operateWithdrawal(Account account, Double amount) {
        Operation operation = new Operation(amount, account, BankOperationType.WITHDRAWAL);
        LOGGER.info("Save a withdrawal : {}", operation);
        operationRepository.save(operation);

    }

    @Override
    public List<Operation> listOperations(Account account) {
        return operationRepository.findAllByAccount(account);
    }
}
