package com.fincontrol.demo.models;

public interface InvestimentoPrecoObserver {
    void update(Investimento investimento);
    void registrarOperacao(Investimento investimento);
}
