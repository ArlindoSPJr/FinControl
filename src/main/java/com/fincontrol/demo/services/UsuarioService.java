package com.fincontrol.demo.services;

import com.fincontrol.demo.exceptions.ResourceNotFoundException;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obterTodos() {return usuarioRepository.findAll();}

    public Usuario obterPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));
    }

    public Usuario obterPorCpf(String cpf){
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + cpf));
    }

    @Transactional
    public Usuario criar(Usuario usuario){return usuarioRepository.save(usuario);}

}
