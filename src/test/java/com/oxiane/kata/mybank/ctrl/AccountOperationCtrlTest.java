package com.oxiane.kata.mybank.ctrl;

import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.domain.BankUser;
import com.oxiane.kata.mybank.exception.UnidentifiedUserException;
import com.oxiane.kata.mybank.service.AccountService;
import com.oxiane.kata.mybank.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountOperationCtrlTest {
    private OperationService operationService;
    private AccountService accountService;
    private AccountOperationCtrl accountOperationCtrl;

    @BeforeEach
    void setUp() {
        operationService = mock(OperationService.class);
        accountService = mock(AccountService.class);
        accountOperationCtrl = new AccountOperationCtrl(operationService, accountService);
    }

    @Test
    void should_call_OperationService_when_deposit_some_value_on_a_account() throws UnidentifiedUserException {
        //Given
        String accountNumber = "12345";
        Double value = 123.0d;
        String user = "JulienQueinnec";
        Principal principal = mock(Principal.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(Boolean.TRUE);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn(user);
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBankUser(new BankUser());
        when(accountService.getAccount(accountNumber, user)).thenReturn(Optional.of(account));
        //When
        accountOperationCtrl.deposit(accountNumber, value, authentication);

        //Then
        Mockito.verify(operationService).operateDeposit(any(Account.class), any());
    }

    @Test
    void should_call_OperationService_when_withdrawal_some_value_on_a_account() throws UnidentifiedUserException {
        //Given
        String accountNumber = "12345";
        Double value = -123.0d;
        String user = "JulienQueinnec";
        Principal principal = mock(Principal.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(Boolean.TRUE);
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn(user);
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBankUser(new BankUser());
        when(accountService.getAccount(accountNumber, user)).thenReturn(Optional.of(account));
        //When
        accountOperationCtrl.withdrawal(accountNumber, value, authentication);

        //Then
        Mockito.verify(operationService).operateWithdrawal(any(Account.class), any());
    }
}
