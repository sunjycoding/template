package com.example.template.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.template.modules.authentication.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author created by sunjy on 12/21/23
 */
@RequiredArgsConstructor
@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwt(request);
        if (StringUtils.hasText(jwt)) {
            try {
                DecodedJWT decodedJwt = tokenService.verifyToken(jwt);
                String userId = decodedJwt.getSubject();
                String username = decodedJwt.getClaim("username").asString();
                List<SimpleGrantedAuthority> authorities = Collections.emptyList();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new RuntimeException("认证失败");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String headerStr = "Authorization";
        String tokenPrefix = "Bearer ";
        String bearerToken = request.getHeader(headerStr);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        }
        return null;
    }

}
