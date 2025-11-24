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
	
	private SecretKey getScreSecretKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public String generateJwt(UserEntity userEntity) {
		return Jwts.builder().subject(userEntity.getUsername()).claim("UserId", String.valueOf(userEntity.getId()))
				.issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getScreSecretKey()).compact();
	}

	public String getUserNameFromToken(String token) {
		Claims claims = Jwts.parser().verifyWith(getScreSecretKey()).build().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	}

}
