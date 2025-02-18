package br.com.medexpress.security.filters;


import br.com.medexpress.security.config.SecurityConfig;
import br.com.medexpress.security.data.UserAuthentication;
import br.com.medexpress.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;


    //quais requisicoes sao publicas e nao necessitam de autenticacao
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return Arrays.stream(SecurityConfig.anonymousMatcher()).anyMatch(it -> it.matches(request)); //percorre o array
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var claims = jwtUtil.resolveClaims(request);
        var authentication = new UserAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authentication); //setando o usuario de autenticacao no contexto do spring security;
        filterChain.doFilter(request, response);
    }
}
