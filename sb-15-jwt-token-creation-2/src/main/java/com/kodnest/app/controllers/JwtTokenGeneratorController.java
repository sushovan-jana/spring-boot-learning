package com.kodnest.app.controllers;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class JwtTokenGeneratorController {
//	I want every time the SIGNING_KEY value will be same... that's why I am giving a constant value generator...	
	private final SecretKey SIGNING_KEY = Keys.hmacShaKeyFor("kodnestacademyverysecurekeyjwt2026".getBytes());

	public String generateToken(String username, String role) {
		String token = Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)).signWith(SIGNING_KEY).compact();
		return token;
	}
	
	@GetMapping("/generate")
	public String generateCookie(@RequestParam String username, @RequestParam String role,
			HttpServletResponse response) {
		
		String jwtToken = generateToken(username, role);
		ResponseCookie cookie = ResponseCookie.from("authToken", jwtToken)
				.httpOnly(true).secure(false).path("/")
				.maxAge(3600).sameSite("none").build();
		
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		
		return "JWT TOKEN SET AS A COOKIE GO AND CHECK!";
	}
	
	@GetMapping("/regenerate")
	public String regenerateCookie(HttpServletRequest request) {
//		1. Get the cookies from the request and store it in Cookie array...
//		request may contain more than one cookie that's why collecting in an array
		Cookie[] arr = request.getCookies();
//		2. Get the cookie value or jwt token by using cookie name(authToken)
		String jwtToken = null;
		if (arr[0].getName().equals("authToken")) {
			jwtToken = arr[0].getValue();
		}
//		3. Call regenerateClaimsFromToken() method
		String[] claims = regenerateClaimsFromToken(jwtToken);
//		4. Traverse String[] and create a message using the contents of array
		String message = "HELLO USER, YOUR USERNAME IS: " + claims[0] + " AND ROLE IS: " + claims[1];
//		5. Return the message as response
		return message;	
	}
	
	public String[] regenerateClaimsFromToken(String token) {
//		1. Parse build the token
//		2. Get claims
		Claims claim = Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();
//		3. get username and role and store in String array
		String[] arr = new String[2];
		arr[0] = claim.getSubject();
		arr[1] = claim.get("role", String.class);
//		4. return String array
		return arr;
	}
}
