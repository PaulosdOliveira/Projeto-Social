package com.github.PaulosdOliveira.social.application.usuario;

import com.github.PaulosdOliveira.social.model.usuario.CadastroUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.LoginUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.CartaoUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.token.Token;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {


    private final UsuarioService service;


    @PostMapping
    public Token cadastarUsuario(@RequestBody @Valid CadastroUsuarioDTO dadosCadastro) {
        String token = service.cadastrarUsuario(dadosCadastro);
        return new Token(token);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> logarUsuario(@RequestBody @Valid LoginUsuarioDTO dadosLogin) {
        String token = service.logarUsuario(dadosLogin);
        return ResponseEntity.ok(new Token(token));
    }

    @PostMapping("/foto")
    @PreAuthorize("isAuthenticated()")
    public void salvarFoto(@RequestParam Long idUsuario, @RequestParam MultipartFile arquivo) throws IOException {
        service.salvarFotoUsuario(idUsuario, arquivo.getBytes());
    }

    @GetMapping("/foto/{idUsuario}")
    public ResponseEntity<byte[]> renderizarFotoUsuario(@PathVariable Long idUsuario) {
        byte[] fotoUsuario = service.buscarFotoUsuario(idUsuario);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(fotoUsuario.length);
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fotoUsuario, headers, HttpStatus.OK);
    }

    @GetMapping(params = "nome")
    public List<CartaoUsuarioDTO> buscarUsuariosPorNome(@RequestParam String nome) {
        return service.findByNomeLike(nome);
    }

    @GetMapping(params = "idUsuario")
    public CartaoUsuarioDTO carregarPerfilUsuario(@RequestParam Long idUsuario) {
        return service.carregarPerfilUsuario(idUsuario);
    }

}
