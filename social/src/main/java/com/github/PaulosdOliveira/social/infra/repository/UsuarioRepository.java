package com.github.PaulosdOliveira.social.infra.repository;

import com.github.PaulosdOliveira.social.model.usuario.NomeUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Usuario findByEmail(String email);


    @Query("Select new com.github.PaulosdOliveira.social.model.usuario.NomeUsuarioDTO(u.id, u.nome) from Usuario u where u.nome like %:nome% order by u.nome limit 10")
    List<NomeUsuarioDTO> buscarPorNome(@Param("nome") String nome);

    @Transactional
    @Modifying
    @Query("Update Usuario u set u.foto = :arquivo where u.id = :id")
    void salvarFoto(@Param("arquivo") byte[] arquivo, @Param("id") Long id);

    @Query("Select u.foto from Usuario u where u.id = :id")
    byte[] findFotoById(Long id);

    @Query("Select new com.github.PaulosdOliveira.social.model.usuario.mensagem.UsuarioDTO(u.id , u.nome) from Usuario u where u.id = :id")
    UsuarioDTO carregarPerfilUsuario(Long id);
}
