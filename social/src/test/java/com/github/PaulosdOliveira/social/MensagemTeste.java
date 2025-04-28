package com.github.PaulosdOliveira.social;

import com.github.PaulosdOliveira.social.application.usuario.mensagem.MensagemService;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.EnvioMensagemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MensagemTeste {

    @Autowired
    private MensagemService service;

    @Test
    public void salvarMensagemTeste(){
        EnvioMensagemDTO mensagemEnviada = new EnvioMensagemDTO(1L, 1L, "Testando o salvamento de mensagens ");
        service.salvarMensagem(mensagemEnviada);
    }

}
