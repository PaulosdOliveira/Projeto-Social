package com.github.PaulosdOliveira.social.webSocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.util.UUID;


@Controller
public class SocketController  {


    @MessageMapping("/mensagem/{idDirect}")
    @SendTo("/direct/{idDirect}")
    public String enviarMensagem(@DestinationVariable UUID idDirect){

        return "";
    }
}
