package com.Security.usermanagement.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Security.usermanagement.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.secret.refresh}")
	private String refreshSecretkey;
	
	@Value("${access_Token.Expiry.time}")
	private long accessTokenExpiryTime;

	@Value("${refresh_Token.Expiry.time}")
	private long refreshTokenExpiryTime;

	private SecretKey getScreSecretKey(String token) {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(token));
	}

	public String generateAccessToken(UserEntity userEntity) {
		return Jwts.builder().subject(userEntity.getUsername()).claim("UserId", String.valueOf(userEntity.getId()))
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + accessTokenExpiryTime))
				.signWith(getScreSecretKey(secretKey)).compact();
		
	}

	public String getUserNameFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getScreSecretKey(secretKey)).build().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}

	public UserEntity validaterefreshToken(String refreshToken) {
		UserEntity userEntity=new UserEntity();
		Claims claims = Jwts.parser().verifyWith(getScreSecretKey(refreshSecretkey)).build().parseSignedClaims(refreshToken).getPayload();
		userEntity.setUserName(claims.getSubject());
		userEntity.setId(Long.valueOf((String) claims.get("UserId")));
		return userEntity;
	}

	public String generaterefreshToken(UserEntity userEntity) {
		return Jwts.builder().subject(userEntity.getUsername()).claim("UserId", String.valueOf(userEntity.getId()))
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + refreshTokenExpiryTime))
				.signWith(getScreSecretKey(refreshSecretkey)).compact();
	}

}
