package com.fincontrol.demo.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;

import java.time.LocalDate;

@Entity
@Table(name = "despesa")
public class Despesa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "despesa_id")
    private Long id;

    @Column(name = "despesa_descricao")
    private String descricao;

    @Column(name = "despesa_valor")
    private double valor;

    @Column(name = "despesa_data")
    private LocalDate data;

    public Despesa() {}

    public Despesa(String descricao, double valor, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
}
