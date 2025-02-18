package br.com.medexpress.security.data;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.List;
import java.util.Optional;

@Getter
public class UserAuthentication extends AbstractAuthenticationToken {

   private final String name;
   private final String email;

   public UserAuthentication(Claims claims){
       super(List.of());
       this.email = claims.getSubject(); //a propriedade principal sempre vai ser uma string
       this.name = claims.get("name", String.class); // retorna a propriedade em formato String
       super.setAuthenticated(true); //o super acessa a classe AbstractAuthenticationToken;
   }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return Optional.of(this.email).orElseThrow();
    }
}
