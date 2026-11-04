package iki.novabank_user_account_service.dto;

public class JwtResponse {
    private String bearToken="bearToken";
    private String token;

    public JwtResponse(String bearToken, String token) {
        this.bearToken = bearToken;
        this.token = token;
    }

    public JwtResponse(){

    }

    public String getBearToken() {
        return bearToken;
    }

    public void setBearToken(String bearToken) {
        this.bearToken = bearToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
