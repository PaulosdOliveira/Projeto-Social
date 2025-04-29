package com.github.PaulosdOliveira.social.model.usuario.mensagem;


import lombok.Data;


@Data
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String urlImagem;

    public UsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.urlImagem = "http://localhost:8080/usuario/foto/" + this.id;
    }
}
