package iki.novabank_user_account_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class JwtUtilsProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.expiration-milliseconds}")
    private Long expirationMilliseconds;

    //generate Key
    private SecretKey keys(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //generate token
    public String generateToken(Authentication authentication){
        String username= authentication.getName();
        Date currentDate=new Date();
        return Jwts.builder()
                .signWith(keys())
                .issuedAt(currentDate)
                .subject(username)
                .expiration(new Date(currentDate.getTime()+expirationMilliseconds))
                .compact();
    }

    //get username from token
    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .verifyWith(keys())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //validate token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith(keys())
                .build()
                .parse(token);
        return true;
    }

}
















