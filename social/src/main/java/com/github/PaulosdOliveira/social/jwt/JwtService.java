package com.github.PaulosdOliveira.social.jwt;

import com.github.PaulosdOliveira.social.model.usuario.UsuarioDTO;
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

    public String getToken(UsuarioDTO usuario) {
        return Jwts.builder()
                .signWith(secretKeyGenerator.getSecret())
                .subject(usuario.getEmail())
                .expiration(getExpiration())
                .claims(getClaims(usuario.getNome(), usuario.getId(), usuario.getUrlFoto()))
                .compact();
    }

    private Date getExpiration() {
        var dataExpiracao = LocalDateTime.now().plusHours(3).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(dataExpiracao);
    }

    private Map<String, Object> getClaims(String nome, Long id, String urlFoto) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        claims.put("id", id);
        claims.put("urlFoto", urlFoto);
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
