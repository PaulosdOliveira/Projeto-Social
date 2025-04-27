package com.github.PaulosdOliveira.social.application.usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class TesteControler {

    @GetMapping
    public String teste() {
        return " EStou funcionando";
    }
}
