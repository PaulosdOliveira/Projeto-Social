package com.github.PaulosdOliveira.social.infra.repository;

import com.github.PaulosdOliveira.social.model.usuario.mensagem.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    @Query("Select m from Mensagem m where m.remetente = :idUsuario " +
           "and m.destinatario = :idAmigo  or m.remetente = :idAmigo " +
           "and m.destinatario = :idUsuario order by m.dataHoraEnvio DESC")
    List<Mensagem> buscarMensagensEnviadas(@Param("idUsuario") Long idUsuario , @Param("idAmigo") Long idAmigo);
}
