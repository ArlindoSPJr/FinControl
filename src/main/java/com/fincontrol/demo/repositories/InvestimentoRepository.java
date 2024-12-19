package com.fincontrol.demo.repositories;

import com.fincontrol.demo.models.Investimento;
import com.fincontrol.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    Optional<Investimento> findById(Long id);
    List<Investimento> findByUsuariosCpf(String cpf);
}
