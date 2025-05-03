package com.github.PaulosdOliveira.social.infra.repository;

import com.github.PaulosdOliveira.social.model.usuario.UsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import com.github.PaulosdOliveira.social.model.usuario.CartaoUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    @Query("Select new com.github.PaulosdOliveira.social.model.usuario.UsuarioDTO(u.id,u.email,  u.nome, u.senha) from Usuario u where u.email = :email")
    UsuarioDTO findByEmail(String email);


    @Query("Select new com.github.PaulosdOliveira.social.model.usuario.CartaoUsuarioDTO(u.id, u.nome) from Usuario u where u.nome like %:nome% order by u.nome limit 10")
    List<CartaoUsuarioDTO> buscarPorNome(@Param("nome") String nome);

    @Transactional
    @Modifying
    @Query("Update Usuario u set u.foto = :arquivo where u.id = :id")
    void salvarFoto(@Param("arquivo") byte[] arquivo, @Param("id") Long id);

    @Query("Select u.foto from Usuario u where u.id = :id")
    byte[] findFotoById(Long id);

    @Query("Select new com.github.PaulosdOliveira.social.model.usuario.UsuarioDTO(u.id , u.nome) from Usuario u where u.id = :id")
    CartaoUsuarioDTO carregarPerfilUsuario(Long id);
}
