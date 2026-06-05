package com.kodnest.app.controllers;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
//import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class TokenController {

	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@GetMapping("/generate")
	public String generateCookie(@RequestParam String username, @RequestParam String role,
			HttpServletResponse response) {
//		JwtBuilder builder = Jwts.builder();
//		builder.setSubject(username);
//		builder.claim("role", role);
//		builder.setIssuedAt(new Date());
//		builder.setExpiration(new Date(System.currentTimeMillis() + 3600000));
//		builder.signWith(key);
//		String token = builder.compact();
//		return "Your JWT Token is : " + token;

//		String token = Jwts.builder().setSubject(username)
//				.claim("role", role).setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
//				.signWith(key).compact();
//		return "Your JWT Token is : " + token; 

//		----- LEGACY WAY OF TOKEN GENERATION AND COOKIE STORING -----		
		String token = generateToken(username, role); // Get the token that has been generated in the server...
		Cookie cookie = new Cookie("authToken", token); // Create Cookie object with name and value...
		cookie.setMaxAge(3600); // After 1 minute browser will automatically remove...
		cookie.setSecure(false); // Not required as we are sending http request...
		cookie.setPath("/");

		response.addCookie(cookie); // Add cookie to response object...
		return "COOKIE ADDED SUCCESSFULLY GO AND CHECK!";
	}

	public String generateToken(String username, String role) {

		String token = Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)).signWith(key).compact();
		return token;
	}

	@GetMapping("/getcookie")
	public String getCookie(HttpServletRequest request) {

//		 Get the cookie from the request...
//		request.getCookies() method will return a array of Cookie object
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("authToken")) {
					String cookieValue = cookie.getValue();
//					Parse + verify cookieValue
//					1. Create JWT parsebuilder object
					JwtParserBuilder parseBuilder = Jwts.parserBuilder(); // Prepare a machine that can read JWT
																			// tokens...
//					2. Give the secret key for verification
					parseBuilder.setSigningKey(key);
//					3. Create parser Object...
					JwtParser parser = parseBuilder.build();
//					4. Parse and verify token
					Jws<Claims> jwsClaims = parser.parseClaimsJws(cookieValue);
					Claims claims = jwsClaims.getBody();
//					5. Get username
					String username = claims.getSubject();
//					6. Extract custom role
					String role = claims.get("role", String.class);

					return "TOKEN VERIFIED SUCCESSFULLY <br><br>" + "USERNAME : " + username + "<br>" + "ROLE : "
							+ role;
				}
			}
		}

		return "NO COOKIE FOUND";
	}

}