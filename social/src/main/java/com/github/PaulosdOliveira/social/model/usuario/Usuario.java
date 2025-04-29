package com.github.PaulosdOliveira.social.model.usuario;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 80, unique = true)
    private  String email;

    @Column(nullable = false, length = 300)
    private String senha;

    @Column()
    private byte[] foto;

    public Usuario(CadastroUsuarioDTO dadosCadastro) {
        BeanUtils.copyProperties(dadosCadastro, this);
    }
}
