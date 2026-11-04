package iki.novabank_user_account_service.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "Email Can't be Blank")
    private String email;
    @NotBlank(message = "Password Can't be Blank")
    private String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public LoginDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
