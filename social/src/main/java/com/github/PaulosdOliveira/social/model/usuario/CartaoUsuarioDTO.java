package com.github.PaulosdOliveira.social.model.usuario;


import lombok.Data;


@Data
public class CartaoUsuarioDTO {
    private Long id;
    private String nome;
    private String urlImagem;

    public CartaoUsuarioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
        this.urlImagem = "http://localhost:8080/usuario/foto/" + this.id;
    }
}
