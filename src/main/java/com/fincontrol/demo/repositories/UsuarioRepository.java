package com.fincontrol.demo.repositories;

import com.fincontrol.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByNome(String nome);
}
