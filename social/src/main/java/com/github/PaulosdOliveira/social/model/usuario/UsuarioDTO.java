package com.github.PaulosdOliveira.social.model.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private String senha;
    private String urlFoto;

    private static String padraoUrlFoto = "http://localhost:8080/usuario/foto/";

    public UsuarioDTO(Long id, String email, String nome, String senha) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.urlFoto = padraoUrlFoto + id;
    }

    public UsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UsuarioDTO (Usuario usuario){
        BeanUtils.copyProperties(usuario, this);
    }
}
