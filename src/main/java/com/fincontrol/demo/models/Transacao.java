package com.fincontrol.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transacao_id")
    private Long id;

    @Column(name = "transacao_data")
    private LocalDate data;

    @Column(name = "transacao_descricao")
    private String descricao;

    @Column(name = "transacao_valor")
    private double valor;

    @Column(name = "transacao_destinatario")
    private String destinatario;

    @ManyToOne
    @JoinColumn(name = "user_cpf")
    @JsonIgnoreProperties("transacoes")
    private Usuario usuario;


    public Transacao() {}

    public Transacao(Long id, LocalDate data, String descricao, double valor, String destinatario,  Usuario usuario) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.destinatario = destinatario;
        this.usuario = usuario;
    }
}
