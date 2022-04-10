package com.oxiane.kata.mybank.ctrl;

import com.fasterxml.jackson.databind.JsonNode;
import com.oxiane.kata.mybank.domain.Account;
import com.oxiane.kata.mybank.exception.AccountUnknowException;
import com.oxiane.kata.mybank.exception.UnidentifiedUserException;
import com.oxiane.kata.mybank.service.AccountService;
import com.oxiane.kata.mybank.service.OperationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/operations")
@OpenAPIDefinition(
        info = @Info(
                title = "Kata myBank : Account operations",
                version = "1.0.0",
                contact = @Contact(
                        name = "Julien Queinnec",
                        email = "jqueinnec@oxiane.com"))
)
public class AccountOperationCtrl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOperationCtrl.class);


    private final OperationService operationService;
    private final AccountService accountService;

    @Autowired
    public AccountOperationCtrl(OperationService operationService, AccountService accountService) {
        this.operationService = operationService;
        this.accountService = accountService;
    }

    @PostMapping(value = "/deposit/{accountNumber}")
    @Operation(summary = "Deposit some money to an account")
    public ResponseEntity<JsonNode> deposit(@PathVariable("accountNumber") @Parameter(description = "number account consider for the operation") final String accountNumber,
                                            @RequestBody @Parameter(description = "Value of the deposit") final Double value, Authentication auth) {
        try {
            final Optional<Account> account = getAccount(getUserConnectedName(auth), accountNumber);
            operationService.operateDeposit(account.orElseThrow(), value);
            return ResponseEntity.ok().build();
        } catch (AccountUnknowException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/withdrawal/{accountNumber}")
    @Operation(summary = "Withdrawal some money from an account")
    public ResponseEntity<JsonNode> withdrawal(@PathVariable("accountNumber") @Parameter(description = "number account consider for the operation") final String accountNumber,
                                               @RequestBody @Parameter(description = "Value of the withdrawal") final Double value, Authentication auth) {
        try {
            final Optional<Account> account = getAccount(getUserConnectedName(auth), accountNumber);
            operationService.operateWithdrawal(account.orElseThrow(), value);
            return ResponseEntity.ok().build();
        } catch (AccountUnknowException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/operations/{accountNumber}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "List of operations for an account")
    public ResponseEntity<List<com.oxiane.kata.mybank.domain.Operation>> listOperations(@PathVariable("accountNumber") @Parameter(description = "number account consider for the operation") final String accountNumber,
                                                                                        Authentication auth) {
        try {
            final Optional<Account> account = getAccount(getUserConnectedName(auth), accountNumber);
            return ResponseEntity.ok().body(operationService.listOperations(account.orElseThrow()));
        } catch (AccountUnknowException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Optional<Account> getAccount(String userConnectedName, String accountNumber) throws AccountUnknowException {
        try {
            final Optional<Account> account = accountService.getAccount(accountNumber, userConnectedName);
            if (account.isEmpty()) {
                LOGGER.error("Unknow account for the user");
                throw new AccountUnknowException();
            }
            return account;
        } catch (UnidentifiedUserException e) {
            throw new AccountUnknowException(e);
        }
    }

    private String getUserConnectedName(Authentication auth) {
        LOGGER.info("Auth : {}, {}", auth.isAuthenticated(), auth.getPrincipal());
        String userConnectedName = "";
        if (auth.getPrincipal() instanceof Principal principal) {
            userConnectedName = principal.getName();
        }
        return userConnectedName;
    }

}
