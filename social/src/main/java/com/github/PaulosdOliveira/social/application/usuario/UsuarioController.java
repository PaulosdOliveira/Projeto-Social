package com.github.PaulosdOliveira.social.application.usuario;

import com.github.PaulosdOliveira.social.model.usuario.CadastroUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.LoginUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.NomeUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.UsuarioDTO;
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
    public ResponseEntity<Void> cadastarUsuario(@RequestBody @Valid CadastroUsuarioDTO dadosCadastro) {
        service.cadastrarUsuario(dadosCadastro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public String logarUsuario(@RequestBody @Valid LoginUsuarioDTO dadosLogin) {
        return service.logarUsuario(dadosLogin);
    }

    @PostMapping("/foto")
    @PreAuthorize("isAuthenticated()")
    public void salvarFoto(@RequestParam Long idUsuario, @RequestParam MultipartFile arquivo) throws IOException {
        service.salvarFotoUsuario(idUsuario, arquivo.getBytes());
    }

    @GetMapping("/foto/{idUsuario}")
    public ResponseEntity<byte[]> renderizarFotoUsuario(@PathVariable Long idUsuario){
        byte[] fotoUsuario = service.buscarFotoUsuario(idUsuario);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(fotoUsuario.length);
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fotoUsuario, headers, HttpStatus.OK);
    }

    @GetMapping("/{nome}")
    public List<NomeUsuarioDTO> buscarUsuariosPorNome(@PathVariable String nome){
        return service.findByNomeLike(nome);
    }

    @GetMapping(params = "idUsuario")
    public UsuarioDTO carregarPerfilUsuario(@RequestParam Long idUsuario){
        return service.carregarPerfilUsuario(idUsuario);
    }

}
