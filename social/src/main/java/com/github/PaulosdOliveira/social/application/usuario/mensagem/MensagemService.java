package com.github.PaulosdOliveira.social.application.usuario.mensagem;

import com.github.PaulosdOliveira.social.model.usuario.mensagem.EnvioMensagemDTO;
import com.github.PaulosdOliveira.social.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.social.infra.repository.UsuarioRepository;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.MensagemDTO;
import com.github.PaulosdOliveira.social.model.usuario.mensagem.Mensagem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import jakarta.validation.Valid;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public MensagemDTO salvarMensagem(@Valid EnvioMensagemDTO mensagemDTO) {
        boolean remetenteExiste = usuarioRepository.existsById(mensagemDTO.getIdRemetente());
        boolean destinatarioExiste = usuarioRepository.existsById(mensagemDTO.getIdRemetente());
        if (destinatarioExiste && remetenteExiste) return(toDTO(repository.save(mensagemDTO.toModel())));
        return null;
    }

    public List<MensagemDTO> buscarMensagens(Long idUsuario, Long idAmigo) {
        // 22:32:46.967229300
        List<Mensagem> mensagens = repository.buscarMensagensEnviadas(idUsuario, idAmigo);
        List<MensagemDTO> mensagensDTO = new ArrayList<>();
        mensagens.forEach(mensagem -> {
            MensagemDTO mensagemDTO = toDTO(mensagem);
            mensagensDTO.add(mensagemDTO);
        });
        return mensagensDTO;
    }

    public MensagemDTO toDTO(Mensagem mensagem) {
        String horaEnvio = LocalTime.from(mensagem.getDataHoraEnvio()).truncatedTo(ChronoUnit.MINUTES).toString();;
        System.out.println("Hora: " + horaEnvio);
        return new MensagemDTO(mensagem.getId(), mensagem.getRemetente(),  mensagem.getMensagem(), horaEnvio);
    }
}
