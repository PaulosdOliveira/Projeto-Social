package com.github.PaulosdOliveira.social.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.PaulosdOliveira.social.application.usuario.mensagem.MensagemService;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.EnvioMensagemDTO;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;



@Controller
public class SocketController {

    @Autowired
    private MensagemService mensagemService;

    @MessageMapping("app/social/mensagem/{idDirect}")
    @SendTo("/mensagem/direct/{idDirect}")
    public String enviarMensagem(@DestinationVariable Long idDirect, EnvioMensagemDTO mensagemEnviada) {
        MensagemDTO mensagem = mensagemService.salvarMensagem(mensagemEnviada);
        return toJson(mensagem);
    }

    public String toJson(MensagemDTO mensagem) {
        try {
            return new ObjectMapper().writeValueAsString(mensagem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
