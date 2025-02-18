package br.com.medexpress.security.config;


import br.com.medexpress.security.filters.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static RequestMatcher[] anonymousMatcher(){
        var anonymousList = List.of(
                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/oauth/login"), //endpoint que a autenticacao sera ignorada
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/swagger-ui/**")
        );
        return anonymousList.toArray(new RequestMatcher[0]);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JWTAuthenticationFilter filterChain) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(filterChain, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth             //define o que vai ser autorizado
                        .requestMatchers(anonymousMatcher()).anonymous() //requestMatcher = tentativa de requisicao ignorando quem esta na lista de anonymousMatcher
                        .anyRequest().authenticated()
                  )
                .build();
    }
}
