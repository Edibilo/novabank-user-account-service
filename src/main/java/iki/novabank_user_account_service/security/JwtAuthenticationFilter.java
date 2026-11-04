package iki.novabank_user_account_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtilsProvider jwtUtilsProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtilsProvider jwtUtilsProvider,
                                   UserDetailsService userDetailsService) {
        this.jwtUtilsProvider = jwtUtilsProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       //get token
        String token=bearerToken(request);

        //validate token
        if(StringUtils.hasText(token) && jwtUtilsProvider.validateToken(token) ){
            String username= jwtUtilsProvider.getUsernameFromToken(token);
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            null,
                            userDetails.getAuthorities()
                    );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }

    //generate Token
    private String bearerToken(HttpServletRequest request){
        String bearerToken=request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
