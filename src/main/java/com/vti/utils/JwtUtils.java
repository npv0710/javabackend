package com.vti.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vti.exception.NotFoundException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils{
	@Value("${npv.jwtSecret}")
	private String JWT_SECRET_KEY;
	
	@Value("${npv.jwtExpirationMs}")
	private int JWT_EXPIRATION_MS;
	
	public String generateJwtToken(Authentication auth) {
		
		UserDetails userPrincipal = (UserDetails) auth.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
				.compact();
	}
	
	public boolean validateJwtToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
			return true;
		}catch(SignatureException ex) {
			System.out.println(ex);
			throw ex;
		}catch(MalformedJwtException ex) {
			System.out.println(ex);
			throw ex;
		}catch(UnsupportedJwtException ex) {
			System.out.println(ex);
			throw ex;
		}catch(ExpiredJwtException ex) {
			System.out.println("Exception in jwt utils: ");
			System.out.println(ex);
			throw ex;
		}catch(IllegalArgumentException ex) {
			System.out.println(ex);
			throw ex;
		}
		
		//return false;
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}
}
