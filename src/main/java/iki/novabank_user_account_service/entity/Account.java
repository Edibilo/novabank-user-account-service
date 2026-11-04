package iki.novabank_user_account_service.entity;

import iki.novabank_user_account_service.enums.AccountStatus;
import iki.novabank_user_account_service.enums.AccountType;
import iki.novabank_user_account_service.enums.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @OneToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist(){
        if(currencyType==null){
            currencyType=CurrencyType.TZS;
        }
        if (accountStatus==null){
            accountStatus=AccountStatus.ACTIVE;
        }
        if(accountType==null){
            accountType=AccountType.SAVING;
        }
    }
    public Account(){

    }

    public Account(Long id, String accountNumber, BigDecimal balance, AccountType accountType,
                   CurrencyType currencyType, AccountStatus accountStatus, User user, LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.currencyType = currencyType;
        this.accountStatus = accountStatus;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
