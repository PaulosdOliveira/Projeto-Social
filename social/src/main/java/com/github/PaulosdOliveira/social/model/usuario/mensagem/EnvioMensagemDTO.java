package com.github.PaulosdOliveira.social.model.usuario.mensagem;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnvioMensagemDTO {

    private Long idRemetente;
    private Long idDestinatario;

    @NotBlank(message = "Não é possível enviar uma mensagem vázia")
    private String mensagem;

    public Mensagem toModel(){
        return new Mensagem(idRemetente, idDestinatario, mensagem);
    }
}
