package iki.novabank_user_account_service.mapper;

import iki.novabank_user_account_service.dto.AccountDto;
import iki.novabank_user_account_service.entity.Account;
import iki.novabank_user_account_service.enums.AccountType;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account=new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountStatus(accountDto.getAccountStatus());
        account.setAccountType(AccountType.SAVING);
        account.setBalance(accountDto.getBalance());
        account.setCreatedAt(accountDto.getCreatedAt());
        account.setCurrencyType(accountDto.getCurrencyType());
        account.setId(accountDto.getId());
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto=new AccountDto();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountStatus(account.getAccountStatus());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBalance(account.getBalance());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setCurrencyType(account.getCurrencyType());
        accountDto.setId(account.getId());
        accountDto.setUserId(account.getUser().getId());
        return accountDto;
    }
}
