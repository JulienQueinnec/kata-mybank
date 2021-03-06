package com.oxiane.kata.mybank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Double amount;
    private BankOperationType operationType;
    private LocalDateTime operationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    public Operation(Double amount, Account account, BankOperationType operationType) {
        this.amount = amount;
        this.account = account;
        this.setOperationDate(LocalDateTime.now());
        this.operationType = operationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BankOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(BankOperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "amount=" + amount +
                ", operationType=" + operationType +
                ", operationDate=" + operationDate +
                ", account=" + account +
                '}';
    }
}
