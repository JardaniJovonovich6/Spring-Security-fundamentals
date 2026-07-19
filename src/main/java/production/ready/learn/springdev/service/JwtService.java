package production.ready.learn.springdev.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import production.ready.learn.springdev.entity.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretToken;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretToken.getBytes(StandardCharsets.UTF_8));
    }

    //This Generates a Token for the New user usually ,
    // also the when a User needed a AcessToken we generate using this function
    public String generateAccessToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email" , user.getEmail())
                .claim("roles" , user.getRoles().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(getSecretKey())
                .compact();

    }

    //This is a Session contorlling Token based for 2 token session System ,
    // this is generated when Session is expired for the User
    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L *60*60*24*30*6))
                .signWith(getSecretKey())
                .compact();

    }

    //used for authentication purpose when we need to get ID from the token
    // given from the Response to the server to verify the JWT token and to maintain security
    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }

}
