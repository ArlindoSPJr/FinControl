package com.fincontrol.demo.services;

import com.fincontrol.demo.controllers.dtos.CreateInvestimentoDto;
import com.fincontrol.demo.exceptions.ResourceNotFoundException;
import com.fincontrol.demo.models.Investimento;
import com.fincontrol.demo.models.InvestimentoPrecoObserver;
import com.fincontrol.demo.models.Logger;
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

    public List<Investimento> obterInvestimentosPorUsuarioCpf(String cpf) {
        return investimentoRepository.findByUsuariosCpf(cpf);
    }

    public Investimento criarInvestimento(Investimento investimento) {
        return this.investimentoRepository.save(investimento);
    }

    public Investimento obterInvestimentoPorId(Long id) {
        Investimento investimento = this.investimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado com ID: " + id));

        // Reinicializar a lista de observadores após recuperar o investimento
        investimento.setObserverList(new ArrayList<>());

        // Adicionar observers padrão, se necessário
        InvestimentoPrecoObserver logger = Logger.getInstance();
        if (!investimento.getObserverList().contains(logger)) {
            investimento.getObserverList().add(logger);
        }

        return investimento;
    }



    public void adicionarObserver(Investimento investimento, InvestimentoPrecoObserver investimentoPrecoObserver) {
        // Inicializar a lista de observadores se estiver nula
        if (investimento.getObserverList() == null) {
            investimento.setObserverList(new ArrayList<>());
            System.out.println("Lista de observadores inicializada.");
        }

        investimento.addObserver(investimentoPrecoObserver);

        System.out.println("Observer adicionado ao investimento.");
    }


    public void removerObserver(Investimento investimento, InvestimentoPrecoObserver observer) {
        if (investimento.getObserverList() != null) {
            investimento.getObserverList().remove(observer);
        }
    }

    private void notificarObservers(Investimento investimento) {
        if (investimento.getObserverList() != null && !investimento.getObserverList().isEmpty()) {
            System.out.println("Notificando observadores...");
            for (InvestimentoPrecoObserver observer : investimento.getObserverList()) {
                if (observer != null) {
                    System.out.println("Chamando update para: " + observer.getClass().getSimpleName());
                    observer.update(investimento);
                } else {
                    System.out.println("Observer inválido encontrado: " + observer);
                }
            }
        } else {
            System.out.println("Lista de observadores está vazia ou nula.");
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
