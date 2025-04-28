package com.github.PaulosdOliveira.social.model.usuario.mensagem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "remetente_id")
    private  Long remetente;

    @Column(name = "destinatario_id")
    private  Long destinatario;

    @Column(nullable = false)
    private  String mensagem;

    @CreatedDate
    private LocalDateTime dataHoraEnvio;


    public Mensagem(Long remetente, Long destinatario, String mensagem) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }
}
