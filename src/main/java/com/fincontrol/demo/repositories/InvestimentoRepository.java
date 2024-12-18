package com.fincontrol.demo.repositories;

import com.fincontrol.demo.models.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    Optional<Investimento> findById(Long id);
}
