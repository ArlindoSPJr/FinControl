package com.fincontrol.demo.repositories;

import com.fincontrol.demo.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    Transacao findById(long id);
    Optional<Transacao> findByData(LocalDate data);
    Optional<Transacao> findByUsuarioCpf(String cpf);
}
