package com.thanhtai.quarantineinformationmanagement.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwtExpirationInMs}")
    private int jwtExpirationInMs;
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("JwtProvider: username=" + userDetails.getUsername() + " pass=" + userDetails.getPassword());

        Date expiryDate = new Date(DateHelper.now.getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        logger.info("getUserNameFromJWT " + claims.toString());
        return claims.getSubject();
    }
    public boolean validateToken(String authToken){
        logger.info("validateToken " + authToken);
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex){
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex){
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex){
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex){
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex){
            logger.error("JWT claims string is empty");
        }
        return false;
    }
}
