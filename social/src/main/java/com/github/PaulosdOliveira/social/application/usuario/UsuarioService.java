package com.github.PaulosdOliveira.social.application.usuario;

import com.github.PaulosdOliveira.social.infra.repository.UsuarioRepository;
import com.github.PaulosdOliveira.social.jwt.JwtService;
import com.github.PaulosdOliveira.social.model.usuario.CadastroUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.LoginUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import com.github.PaulosdOliveira.social.validation.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioValidator validator;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;



    public void cadastrarUsuario(CadastroUsuarioDTO dadosCadastro){
       dadosCadastro.setSenha(encoder.encode(dadosCadastro.getSenha()));
        validator.validar(dadosCadastro.getEmail());
        repository.save(new Usuario(dadosCadastro));
    }

    public String logarUsuario(LoginUsuarioDTO dadosLogin){
        var usuario = repository.findByEmail(dadosLogin.getEmail());
        if(usuario != null){
            String senhaDigitada = dadosLogin.getSenha();
            if(encoder.matches(senhaDigitada, usuario.getSenha())) return jwtService.getToken(usuario);
        }
        throw new UsernameNotFoundException("Email e/ou senha incorretos");
    }

    public Usuario findByEmail(String email){
        return repository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }
}
