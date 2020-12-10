package com.auth.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String secretkey = "${jwt.secret}";

	public String extractUsername(String token) {
		try {

			if (!token.substring(0, 7).equals("Bearer ")) {
				return null;
			}

			String token1 = token.substring(7);
			return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	public String generateToken(UserDetails userDetails) {
		String compact = Jwts.builder().setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))// token for 15 min
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return compact;
	}

	public Boolean validateToken(String token) {
		try {

			if (!token.substring(0, 7).equals("Bearer ")) {
				return false;
			}

			String token1 = token.substring(7);
			Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token1).getBody().getExpiration();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}