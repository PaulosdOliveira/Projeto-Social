package com.github.PaulosdOliveira.social.application.usuario;

import com.github.PaulosdOliveira.social.infra.repository.UsuarioRepository;
import com.github.PaulosdOliveira.social.jwt.JwtService;
import com.github.PaulosdOliveira.social.model.usuario.CadastroUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.LoginUsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.UsuarioDTO;
import com.github.PaulosdOliveira.social.model.usuario.Usuario;
import com.github.PaulosdOliveira.social.model.usuario.CartaoUsuarioDTO;
import com.github.PaulosdOliveira.social.validation.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioValidator validator;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;


    public UsuarioDTO findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String cadastrarUsuario(CadastroUsuarioDTO dadosCadastro) {
        validator.validar(dadosCadastro.getEmail());
        dadosCadastro.setSenha(encoder.encode(dadosCadastro.getSenha()));
        var usuarioSalvo = repository.save(new Usuario(dadosCadastro));
        return jwtService.getToken(new UsuarioDTO(usuarioSalvo));
    }

    public String logarUsuario(LoginUsuarioDTO dadosLogin) {
        UsuarioDTO usuario = repository.findByEmail(dadosLogin.getEmail());
        if (usuario != null) {
            String senhaDigitada = dadosLogin.getSenha();
            if (encoder.matches(senhaDigitada, usuario.getSenha())) {
                return jwtService.getToken(usuario);
            }
            throw new UsernameNotFoundException("Email e/ou senha incorretos");
        }
        throw new UsernameNotFoundException("Este email não está cadastrado");
    }

    @Transactional
    public void salvarFotoUsuario(Long idUsuario, byte[] arquivo) {
        repository.salvarFoto(arquivo, idUsuario);
    }

    public byte[] buscarFotoUsuario(Long idUsuario) {
        return repository.findFotoById(idUsuario);
    }

    public List<CartaoUsuarioDTO> findByNomeLike(String nome) {
        return repository.buscarPorNome(nome);
    }

    public CartaoUsuarioDTO carregarPerfilUsuario(Long id) {
        return repository.carregarPerfilUsuario(id);
    }

}
