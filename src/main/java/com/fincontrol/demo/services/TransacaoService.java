package com.fincontrol.demo.services;

import com.fincontrol.demo.controllers.dtos.UpdateTransacaoDto;
import com.fincontrol.demo.exceptions.ResourceNotFoundException;
import com.fincontrol.demo.models.Transacao;
import com.fincontrol.demo.repositories.TransacaoRepository;
import com.fincontrol.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Transacao> obterTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao obterPorId(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada com id:" + id));
    }

    public Transacao obterPorData(LocalDate data) {
        return transacaoRepository.findByData(data)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada na data: " + data));
    }

    public List<Transacao> obterPorUserCpf(String cpf) {
        return transacaoRepository.findByUsuarioCpf(cpf);
    }

    public void criarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    public void excluirTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public void atualizarTransacao(Transacao transacao) {
        transacaoRepository.save(transacao);
    }

    @Transactional
    public void atualizarById(Long id, UpdateTransacaoDto updateTransacaoDto) {
        Transacao transacao = transacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada com id:" + id));

        updateField(updateTransacaoDto.data(), transacao::setData);
        updateField(updateTransacaoDto.valor(), transacao::setValor);
        updateField(updateTransacaoDto.descricao(), transacao::setDescricao);
        updateField(updateTransacaoDto.destinatario(), transacao::setDestinatario);

        transacaoRepository.save(transacao);
    }

    private <T> void updateField(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }
}
