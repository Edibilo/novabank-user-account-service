package iki.novabank_user_account_service.kafka.dto;

public class UserRegistrationEvent {
    private String email;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String bankName="NOVA BANK";

    public UserRegistrationEvent(String email, String firstName,
                                 String lastName, String accountNumber,
                                 String bankName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    public UserRegistrationEvent(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
