package com.github.PaulosdOliveira.social.jwt;

import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private SecretKeyGenerator secretKeyGenerator;

    public String getToken(Usuario usuario) {
        return Jwts.builder()
                .signWith(secretKeyGenerator.getSecret())
                .subject(usuario.getEmail())
                .expiration(getExpiration())
                .claims(getClaims(usuario.getNome(), usuario.getId()))
                .compact();
    }

    public Date getExpiration() {
        var dataExpiracao = LocalDateTime.now().plusHours(3).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(dataExpiracao);
    }

    public Map<String, Object> getClaims(String nome, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Nome", nome);
        claims.put("id", id);
        return claims;
    }

    public String getEmailByToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKeyGenerator.getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
