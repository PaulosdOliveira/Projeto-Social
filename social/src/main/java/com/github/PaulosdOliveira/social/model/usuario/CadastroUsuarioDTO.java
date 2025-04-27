package com.github.PaulosdOliveira.social.model.usuario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CadastroUsuarioDTO {
    @NotBlank(message = "Preencha todos os campos")
    private String nome;

    @Email(message = "Insira um email válido")
    @NotBlank(message = "Preencha todos os campos")
    private String email;

    @NotBlank(message = "Preencha todos os campos")
    private String senha;
}
