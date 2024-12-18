package com.fincontrol.demo.services;

import com.fincontrol.demo.controllers.dtos.UpdateUserDto;
import com.fincontrol.demo.exceptions.ResourceNotFoundException;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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

    @Transactional
    public void atualizarUserByCpf(String userCpf, UpdateUserDto updateDto) {
        Usuario user = usuarioRepository.findByCpf(userCpf)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com CPF: " + userCpf));

        updateField(updateDto.nome(), user::setNome);
        updateField(updateDto.email(), user::setEmail);
        updateField(updateDto.cpf(), user::setCpf);
        updateField(updateDto.password(), user::setPassword);

        usuarioRepository.save(user);
    }

    private <T> void updateField(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }

    public void excluirPorCpf(String cpf){
        Usuario usuario = obterPorCpf(cpf);
        usuarioRepository.delete(usuario);
    }

}
