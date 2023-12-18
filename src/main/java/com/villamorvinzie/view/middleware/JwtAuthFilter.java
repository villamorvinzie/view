package com.villamorvinzie.view.middleware;

import com.villamorvinzie.view.dto.response.ErrorResponseDto;
import com.villamorvinzie.view.service.JwtService;
import com.villamorvinzie.view.service.UserService;
import com.villamorvinzie.view.util.CustomObjectMapper;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(BEARER)) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = authHeader.substring(BEARER.length());
            String username = jwtService.getSubject(jwt);
            if (!StringUtils.isBlank(username)) {
                UserDetails reqUser = userService.loadUserByUsername(username);
                if (jwtService.isJwtValid(jwt, reqUser)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null, reqUser.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(auth);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
        } catch (JwtException ex) {
            ErrorResponseDto ret = new ErrorResponseDto(ex.getMessage(), LocalDateTime.now(), null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(CustomObjectMapper.convertObjToStr(ret));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
