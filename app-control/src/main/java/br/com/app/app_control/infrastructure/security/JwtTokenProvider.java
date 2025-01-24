package br.com.app.app_control.infrastructure.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.project.core.entities.Usuario_;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Provides functionality for creating, parsing, and validating JSON Web Tokens (JWTs).
 */
@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    /**
     * Creates a JWT token containing the username and roles.
     *
     * @param username the username to be included in the token.
     * @param roles the set of roles to be included in the token.
     * @return the generated JWT token as a string.
     */
    public String createToken(String name, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .claim(Usuario_.NOME, name)
                .claim(Usuario_.EMAIL, email)
                .compact();
    }

    /**
     * Extracts the username from the given JWT token.
     *
     * @param token the JWT token.
     * @return the username extracted from the token.
     */
    public String getEmail(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody()
                   .get(Usuario_.EMAIL, String.class);
    }

    /**
     * Validates the given JWT token.
     *
     * @param token the JWT token to be validated.
     * @return true if the token is valid; false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Resolves and extracts the JWT token from the Authorization header of the request.
     *
     * @param request the HttpServletRequest containing the Authorization header.
     * @return the JWT token extracted from the header, or null if not present.
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
