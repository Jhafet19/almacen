package mx.edu.utez.almacen.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import mx.edu.utez.almacen.model.user.UserBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_TYPE = "Bearer ";

    public String generateToken(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + expiration * 1000);

        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date()).setExpiration(tokenValidity)
                .signWith(getSingKey(), SignatureAlgorithm.HS256).compact();
    }
    private Key getSingKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseJwtClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSingKey())
                .build().parseClaimsJws(token).getBody();
    }

    public  Claims resolveClaims(HttpServletRequest request){
        try {
            String token =resolaveToken(request);
            if(token!=null){
                return parseJwtClaims(token);
            }
            return null;
        }catch (ExpiredJwtException e){
            throw e;
        }catch (Exception e){
            throw e;
        }

    }

    public String resolaveToken(HttpServletRequest request) {

    String bearerToken= request.getHeader(TOKEN_HEADER);
    if (bearerToken!=null && bearerToken.startsWith(TOKEN_TYPE)){
        return  bearerToken.replace(TOKEN_TYPE,"");
    }
        return null;
    }

    public boolean validateClaims(Claims claims,String token){
        try {
            parseJwtClaims(token);
            return claims.getExpiration().after(new Date());

        }catch (MalformedJwtException | UnsupportedJwtException | ExpiredJwtException e){
            e.printStackTrace();
        }catch (Exception e){
            throw  e;
        }
        return false;
    }


}
