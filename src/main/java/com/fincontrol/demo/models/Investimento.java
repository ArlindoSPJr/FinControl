package com.fincontrol.demo.models;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "investimento")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investimento_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "investimento_tipo")
    private TipoInvestimento tipoInvestimento;

    @Column(name = "investimento_minimo")
    private Double minimo;

    public Investimento() {}

    public Investimento(TipoInvestimento tipoInvestimento, Double minimo, Long id) {
        this.tipoInvestimento = tipoInvestimento;
        this.minimo = minimo;
        this.id = id;
    }
}
