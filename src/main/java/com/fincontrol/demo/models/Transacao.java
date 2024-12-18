package com.fincontrol.demo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transacao_id")
    private Long id;

    @Column(name = "transacao_data_hora")
    private LocalDateTime data_horario;

    @Column(name = "transacao_descricao")
    private String descricao;

    @Column(name = "transacao_valor")
    private double valor;

    public Transacao() {}

    public Transacao(Long id, LocalDateTime data_horario, String descricao, double valor) {
        this.id = id;
        this.data_horario = data_horario;
        this.descricao = descricao;
        this.valor = valor;
    }
}
