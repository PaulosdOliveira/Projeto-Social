package com.github.PaulosdOliveira.social;

import com.github.PaulosdOliveira.social.infra.repository.ChaveSecretaRepository;
import com.github.PaulosdOliveira.social.model.usuario.key.ChaveSecreta;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

@SpringBootTest
public class CriarChaveTeste {


    @Autowired
    private ChaveSecretaRepository repository;

    @Test
    public void salvarChaveSecreta(){
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        ChaveSecreta chaveSecreta = new ChaveSecreta();
        chaveSecreta.setEncoded(secretKey.getEncoded());
        chaveSecreta.setAlgoritimo(secretKey.getAlgorithm());
        repository.save(chaveSecreta);
    }

}
