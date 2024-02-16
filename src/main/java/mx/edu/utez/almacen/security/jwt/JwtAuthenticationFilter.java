package mx.edu.utez.almacen.security.jwt;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
    import mx.edu.utez.almacen.security.service.UserDetailsServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider provider;

    @Autowired
    private UserDetailsServiceImplement serviceImplement;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = provider.resolaveToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                Claims claims = provider.resolveClaims(request);
                if (claims != null && provider.validateClaims(claims, token)) {
                    String username = claims.getSubject();
                    UserDetails userDetails = serviceImplement.loadUserByUsername(username);
                    Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(request, response);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
        }
    }
}
