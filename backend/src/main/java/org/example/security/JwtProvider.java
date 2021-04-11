package org.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;

import java.net.Authenticator;
import java.security.Key;

import io.jsonwebtoken.security.Keys;
import org.example.data.entity.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;
    private final JwtParser jwtParser;

    public JwtProvider(@Value("${jwtSecretKey}") String key) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build();
    }

    public String generateToken(String username, Role role){
        Date exdate = Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(username)
                .claim("Role", role.toString())
                .setExpiration(exdate)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            jwtParser.parse(token);
            return true;
        } catch (Exception e) {
            System.err.println("Invalid token");
            return false;
        }
    }

    public Claims parseClaims(String token){
        return jwtParser.parseClaimsJws(token).getBody();
    }

//    public Authentication getAuthentication(String token){
//        return new
//    }

    public String getUsername(String token){
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }

}
