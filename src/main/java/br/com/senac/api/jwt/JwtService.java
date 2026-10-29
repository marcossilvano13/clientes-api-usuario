package br.com.senac.api.jwt;

import br.com.senac.api.entidades.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    private String segredo = "cvHWa40A18vP7TvwCr7EvdWW60yidXPgpXVWslDK04X";

    public String gerarToken(Usuarios usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                    .withIssuer("cliente-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException e){
            throw e;
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.require(algorithm)
                    .withIssuer("cliente-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return null;

        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now(). //pega a data e hora atual
                plusHours(1). //soma uma a data atual
                toInstant(ZoneOffset.of("-03:00"));
    }
}
