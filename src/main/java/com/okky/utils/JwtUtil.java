package com.okky.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtil {

	public String createToken(Long test1, String test2) throws InvalidKeyException, UnsupportedEncodingException {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = Jwts.builder().setSubject("1234567890").setId("3afb607e-74b4-4d33-b0ef-b52bc337c186")
				.setIssuedAt(new Date()).setExpiration(cal.getTime()).claim("name", "John Doe").claim("admin", true)
				.signWith(SignatureAlgorithm.HS256,
						"123456781234567812345678123456781234567812345678".getBytes("UTF-8"))
				.compact();

		try {
			s += "a";
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey("123456781234567812345678123456781234567812345678".getBytes("UTF-8"))
					.parseClaimsJws(s);

			Date exp = claims.getBody().getExpiration();
			Date now = new Date();

			if (exp.after(now)) {
				return "true";
			}

			return "false";
			// OK, we can trust this JWT

		} catch (SignatureException e) {
			return e.toString();
			
			// don't trust the JWT!
		}
	}

	public Claims getClaims(String token) {

		try {

			
			Claims claims = Jwts.parser()
					.setSigningKey("123456781234567812345678123456781234567812345678".getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();

			return claims;

	}catch (Exception e) {
		return null;
	}
}
}