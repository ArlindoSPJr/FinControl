package com.fincontrol.demo.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "investimento")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investimento_id")
    private Long id;

    @Column(name = "preco")
    private double preco;

    @Enumerated(EnumType.STRING)
    @Column(name = "investimento_tipo")
    private TipoInvestimento tipoInvestimento;

    @Column(name = "investimento_minimo")
    private Double minimo;

    @Transient
    private List<InvestimentoPrecoObserver> observerList = new ArrayList<>();

    public Investimento() {}

    public Investimento(double preco, TipoInvestimento tipoInvestimento, Double minimo) {
        this.preco = preco;
        this.tipoInvestimento = tipoInvestimento;
        this.minimo = minimo;
        this.observerList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(TipoInvestimento tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public List<InvestimentoPrecoObserver> getObserverList() {
        return observerList;
    }

    public void setObserverList(List<InvestimentoPrecoObserver> observerList) {
        this.observerList = observerList;
    }
}
