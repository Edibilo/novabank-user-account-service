package iki.novabank_user_account_service.dto;

import iki.novabank_user_account_service.enums.AccountStatus;
import iki.novabank_user_account_service.enums.AccountType;
import iki.novabank_user_account_service.enums.CurrencyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private CurrencyType currencyType;
    private AccountStatus accountStatus;
    private Long userId;
    private LocalDateTime createdAt;

    public AccountDto(){

    }

    public AccountDto(Long id, String accountNumber, BigDecimal balance, AccountType accountType,
                      CurrencyType currencyType, AccountStatus accountStatus, Long userId,
                      LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.currencyType = currencyType;
        this.accountStatus = accountStatus;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
