package com.example.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
    	// here it will check if the token has created before time limit. i.o 10 hours then will return true  else false
        return extractExpiration(token).before(new Date());
    }
     
    // this method is for generating token. as argument is username .so as user send request with username and password 
     // so there will be fetch the username .so based on that username we are going to create one token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
     
    //in this method creattoken subject argument is username
    // here we are setting the time for 10 hours to expire the token
    //and you can see we are using HS256 algorithm 
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
     //here we are validate the token 
    public Boolean validateToken(String token, UserDetails userDetails) {
    	// basically token will be generated in encrpted string and form that string . we extract our username and password using extractusername method
        final String username = extractUsername(token);
        // here we are validation the username and check the token is expired or not
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
