package com.github.PaulosdOliveira.social.application.usuario.mensagem;

import com.github.PaulosdOliveira.social.model.usuario.mensagem.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<MensagemDTO> buscarMensagens(@RequestParam(name = "idUsuario") Long idUsuario, @RequestParam("idAmigo") Long idAmigo) {
        return service.buscarMensagens(idUsuario, idAmigo);
    }
}
