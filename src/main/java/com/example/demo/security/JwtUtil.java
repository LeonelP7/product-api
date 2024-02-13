package com.example.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;

import static io.jsonwebtoken.Jwts.builder;

public class JwtUtil {

    private static final String SECRET = "your-secret-key";
    private static final long EXP_DATE_10_DAYS = 864_000_000;

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_DATE_10_DAYS))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static String getUsername(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getAudience();
    }

    //method for validate token
    public static boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;

        }catch (SignatureException ex){
            System.out.println("Signature exception");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT exception");
        }

        return false;
    }
}
