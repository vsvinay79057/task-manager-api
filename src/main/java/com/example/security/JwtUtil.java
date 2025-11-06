package com.example.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {


	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;


private Key key() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
}
	
	public String generateToken(String username) {
		Date now = new Date();
		Date exp = new Date(now.getTime()+jwtExpirationMs);
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(key(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String getUsernameFromJwt(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateJwt(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
			return true;
		}
		catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
