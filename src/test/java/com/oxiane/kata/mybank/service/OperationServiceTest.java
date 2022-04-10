package com.oxiane.kata.mybank.service;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.BankUser;
import com.oxiane.kata.mybank.domain.Operation;
import com.oxiane.kata.mybank.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class OperationServiceTest {
    @Test
    void should_create_an_bank_operation_when_ask_far_create_a_deposit() {
        //Given
        Account account = new Account("12345", new BankUser("Julien", "Queinnec"));
        Double amount = 100.0;
        OperationRepository operationRepository = mock(OperationRepository.class);
        OperationService operationService = new OperationServiceImpl(operationRepository);

        //When
        operationService.operateDeposit(account, amount);
        //Then
        Mockito.verify(operationRepository).save(any(Operation.class));
    }
}
