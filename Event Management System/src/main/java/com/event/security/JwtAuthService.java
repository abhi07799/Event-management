package com.event.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.event.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtAuthService
{
	private final String SECRET_KEY = "17b3c2cd43338ad0124a7962c405bba1615fae6b4a7cc9da9942ffe1a68bc84d";
	
	private int tokenExpirationTime = 60 * 60 * 1000;
	
	
	// to generate the token
	public String generateToken(UserModel user)
	{
		String token = Jwts.builder().subject(user.getUsername()).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + tokenExpirationTime)).signWith(getSigningKey())
				.compact();

		return token;
	}
	
	//to create a signing key to generate a token
	private SecretKey getSigningKey()
	{
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	//to extract claims or information from the payload
	//this method is created to extract all the information such as subject, issuedAt or expiration etc. from the token we generated
	private Claims extractAllClaims(String token)
	{
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}
	
	//created to extract a specific property or information from the token
	private <T> T extractClaim(String token, Function<Claims, T> resolver)
	{
		Claims claim = extractAllClaims(token);
		return resolver.apply(claim);
	}
	
	//created to extract the username 
	public String extractUsername(String token)
	{
		// we are getting the subject because when we generated the token we put the username in subject parameter
		return extractClaim(token, Claims::getSubject);
	}
	
	//created to validate the token
	// we will check whether the username  from payload and the username from UserDetails are same or not
	public boolean isValid(String token, UserDetails user)
	{
		String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

	//to check whether token is expired or not
	private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
	
	//this method is created so that we can get the expiration time from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
