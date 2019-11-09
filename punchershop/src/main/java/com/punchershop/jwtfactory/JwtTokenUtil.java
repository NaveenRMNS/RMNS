package com.punchershop.jwtfactory;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.punchershop.jwtfactory.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Naveen
 *
 */
@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = -8740083120190940335L;
	
	static final String CLAM_KEY_USERNAME = "sub";
	static final String CLAM_KEY_AUDIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;

	public String getUsernameFromToken(String token) {
	String username = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}
		catch(Exception exception) {
			username = null;
		}
		return username;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		JwtUser jwtUser = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(jwtUser.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {
		Date expirationDate = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			if(claims != null) {
				expirationDate = claims.getExpiration();
			}
		} catch (Exception e) {
			expirationDate = null;
		}
		return expirationDate;
	}

	public String generateToken(JwtUser userDetails) {
		Map<String, Object> claimsMap = new HashMap<String, Object>();
		claimsMap.put(CLAM_KEY_USERNAME, userDetails.getUsername());
		claimsMap.put(CLAM_KEY_CREATED, new Date());
		return generateToken(claimsMap);
	}

	private String generateToken(Map<String, Object> claimsMap) {
		return Jwts.builder().setClaims(claimsMap).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private Date generateExpirationDate() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis()+ expiration * 1000);
	}

}
