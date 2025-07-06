package com.learning.security.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	private String secretKey = "";

	public JWTService() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
		SecretKey sk = keyGen.generateKey();
		secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
	}

	public String generateToken(String userName) {

		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().claims().add(claims).subject(userName).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
				.and()
				.signWith(getkey())
				.compact();
	}

	
	private Key getkey() {
		byte[] decode = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(decode);
	}
	 

	public String extractUsernameFromToken(String token) {
		return extractClaim(token, Claims::getSubject);

	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}

	
	private SecretKey getSecretKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUsernameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isExpiredToken(token));
	}

	private boolean isExpiredToken(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}
