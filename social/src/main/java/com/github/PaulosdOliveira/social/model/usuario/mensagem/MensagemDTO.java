package com.github.PaulosdOliveira.social.model.usuario.mensagem;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class MensagemDTO {
    private Long id;
    private Long idRemetente;
    private String mensagem;
    private String HoraEnvio;
}
