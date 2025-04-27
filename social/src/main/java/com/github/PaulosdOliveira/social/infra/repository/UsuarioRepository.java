package com.github.PaulosdOliveira.social.infra.repository;

import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Usuario findByEmail(String email);
}
