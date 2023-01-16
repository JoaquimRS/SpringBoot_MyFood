package com.myfood.springboot_myfood.security.jwt;

import com.myfood.springboot_myfood.security.AuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends GenericFilter {
    public static final String TOKEN_PREFIX = "Bearer";
    private final JwtUtils jwtUtils;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional.ofNullable(((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith(TOKEN_PREFIX))
                .map(authHeader -> authHeader.substring(TOKEN_PREFIX.length()))
                .filter(jwtUtils::validateToken)
                .map(jwtUtils::getSub)
                .map(authenticationProvider::getAuthentication)
                .ifPresent(SecurityContextHolder.getContext()::setAuthentication);

//        if (((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION) == null) {
//            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The Token is Not valid");
//        }

        chain.doFilter(request, response);
    }
}
