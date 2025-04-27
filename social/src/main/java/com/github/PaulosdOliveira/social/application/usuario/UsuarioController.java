package com.github.PaulosdOliveira.social.application.usuario;

import com.github.PaulosdOliveira.social.model.usuario.CadastroUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.LoginUsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {


    private final UsuarioService service;


    @PostMapping
    public ResponseEntity<Void> cadastarUsuario(@RequestBody @Valid CadastroUsuarioDTO dadosCadastro) {
        service.cadastrarUsuario(dadosCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public String logarUsuario(@RequestBody @Valid LoginUsuarioDTO dadosLogin) {
        return service.logarUsuario(dadosLogin);
    }

}
