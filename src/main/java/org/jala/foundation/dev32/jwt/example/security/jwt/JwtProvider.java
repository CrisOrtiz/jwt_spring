package org.jala.foundation.dev32.jwt.example.security.jwt;

import org.jala.foundation.dev32.jwt.example.security.entities.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

  private final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
  private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
  private static final String CLAIM_KEY = "authorities";
  private static final long THOUSAND = 1000;

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private int expiration;

  @Value("${jwt.auth.issuer}")
  private String issuer;

  @Value("${jwt.auth.audience}")
  private String audience;

  public String generateToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    Date now = new Date();
    return Jwts.builder().setSubject(userPrincipal.getUsername())
        .setIssuer(issuer)
        .setAudience(audience)
        .claim(CLAIM_KEY, userPrincipal.getAuthorities())
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + expiration * THOUSAND))
        .signWith(SIGNATURE_ALGORITHM, secret)
        .compact();
  }

  public String getUserNameFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException exception) {
      logger.error("Malformed token");
    } catch (UnsupportedJwtException exception) {
      logger.error("Unsupported token");
    } catch (ExpiredJwtException exception) {
      logger.error("Expired token");
    } catch (IllegalArgumentException exception) {
      logger.error("Empty token");
    } catch (SignatureException exception) {
      logger.error("Fail on signature");
    }
    return false;
  }
}
