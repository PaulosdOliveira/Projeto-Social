package com.github.PaulosdOliveira.social.infra.repository;

import com.github.PaulosdOliveira.social.model.usuario.key.ChaveSecreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChaveSecretaRepository  extends JpaRepository<ChaveSecreta, Integer> {

    @Query( nativeQuery = true, value = "Select * from chave_secreta c limit 1")
    ChaveSecreta buscarChave();
}
