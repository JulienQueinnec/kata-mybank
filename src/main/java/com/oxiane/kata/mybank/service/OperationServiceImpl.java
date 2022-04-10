package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.Operation;
import com.oxiane.kata.mybank.repository.OperationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
    private static Logger LOGGER = LoggerFactory.getLogger(OperationServiceImpl.class);

    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void operateDeposit(Account account, Double amount) {
        Operation operation = new Operation(amount, account);
        LOGGER.info("Save a operation : {}", operation);
        operationRepository.save(operation);
    }
}
