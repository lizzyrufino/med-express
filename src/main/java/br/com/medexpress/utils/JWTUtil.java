package br.com.medexpress.utils;


import br.com.medexpress.configuration.ApplicationConfig;
import br.com.medexpress.infraestructure.repository.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JWTUtil {
    private final ApplicationConfig applicationConfig;
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public String createToken(User user){
        try{
            var claims = Jwts.claims().setSubject(user.getEmail());
            claims.put("name", user.getName()); //adicionando os parametros do jwt
            var tokenCreationDate = new Date(); //data de criacao do token
            var tokenValidity = new Date(tokenCreationDate.getTime() + TimeUnit.MINUTES.toMillis(applicationConfig.getExpiresIn()*1000)); //data de validade do token
            var key = Keys.hmacShaKeyFor(applicationConfig.getSecretKey().getBytes()); //definindo a chave de criptografia do jwt
            return Jwts.builder().setClaims(claims).setExpiration(tokenValidity).signWith(key, SignatureAlgorithm.HS256).compact(); //criacao do token
        }catch (Exception e){
            throw e;
        }
    }

    //recebe o token e descompacta ele;
    private Claims parseJwtClaims(String token) {
        var key = Keys.hmacShaKeyFor(applicationConfig.getSecretKey().getBytes());
        var jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        return jwtParser.parseClaimsJws(token).getBody(); //Claims eh um objeto do java q acessa o json com o jwt
    }

    //recebe a requisicao e captura o token
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    //retorna o token
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length()); //removendo a palavra Bearer do token e devolvendo o jwt puro
        }
        return null;
    }
}
