package com.oxiane.kata.mybank.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OperationServiceTest {
    @Test
    void should_create_an_bank_operation_when_ask_far_create_a_deposit() {
        //Given
        Account account = new Account("12345", new BankUser("Julien", "Queinnec"));
        Double amount = 100.0;

        //When
        operationService.operateDeposit(account, amount);
        //Then
        Mockito.verify(oerationRepository.save(account, amount));
    }
}
