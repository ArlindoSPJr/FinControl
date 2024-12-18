package com.fincontrol.demo.services;

import com.fincontrol.demo.controllers.dtos.CreateInvestimentoDto;
import com.fincontrol.demo.exceptions.ResourceNotFoundException;
import com.fincontrol.demo.models.Investimento;
import com.fincontrol.demo.models.InvestimentoPrecoObserver;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.repositories.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;

    public InvestimentoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    public List<Investimento> obterInvestimentos() {
        return investimentoRepository.findAll();
    }

    public Investimento criarInvestimento(Investimento investimento) {
        return this.investimentoRepository.save(investimento);
    }

    public Investimento obterInvestimentoPorId(Long id) {
        return this.investimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado com ID: " + id));
    }

    public void adicionarObserver(Investimento investimento, InvestimentoPrecoObserver observer) {
        if (investimento.getObserverList() == null) {
            investimento.setObserverList(new ArrayList<>());
        }
        investimento.getObserverList().add(observer);
    }

    public void removerObserver(Investimento investimento, InvestimentoPrecoObserver observer) {
        if (investimento.getObserverList() != null) {
            investimento.getObserverList().remove(observer);
        }
    }

    private void notificarObservers(Investimento investimento) {
        if (investimento.getObserverList() != null) {
            for (InvestimentoPrecoObserver observer : investimento.getObserverList()) {
                observer.update(investimento.getPreco());
            }
        }
    }

    public void alterarValor(Investimento investimento, double novoValor) {
        if (investimento.getPreco() != novoValor) {
            investimento.setPreco(novoValor);
            this.investimentoRepository.save(investimento);
            this.notificarObservers(investimento);
        }
    }

}
