package com.github.PaulosdOliveira.social.jwt;

import com.github.PaulosdOliveira.social.infra.repository.ChaveSecretaRepository;
import com.github.PaulosdOliveira.social.model.usuario.key.ChaveSecreta;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


@Component
public class SecretKeyGenerator {

    private SecretKey chaveSecreta;

    @Autowired
    private ChaveSecretaRepository repository;


    public SecretKey getSecret(){
        if(chaveSecreta == null){
            ChaveSecreta chaveSalva = repository.buscarChave();
            chaveSecreta = new SecretKeySpec(chaveSalva.getEncoded(), 0, chaveSalva.getEncoded().length, chaveSalva.getAlgoritimo());
        }
        return chaveSecreta;
    }
}
