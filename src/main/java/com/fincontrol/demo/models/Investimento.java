package com.fincontrol.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "investimento")
@JsonIgnoreProperties({"observerList"})
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_investimento",
            joinColumns = @JoinColumn(name = "investimento_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id") // Ajuste para a chave correta de Usuario
    )
    private Set<Usuario> usuarios = new HashSet<>();

    @Transient
    @JsonIgnore
    private List<InvestimentoPrecoObserver> observerList = new ArrayList<>();

    public Investimento() {
        this.observerList = new ArrayList<>();
    }

    public Investimento(double preco, TipoInvestimento tipoInvestimento, Double minimo) {
        this.preco = preco;
        this.tipoInvestimento = tipoInvestimento;
        this.minimo = minimo;
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

    public void addObserver(InvestimentoPrecoObserver observer) {
        observerList.add(observer);
    }
}
