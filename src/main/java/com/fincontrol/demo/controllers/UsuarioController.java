package com.fincontrol.demo.controllers;

import com.fincontrol.demo.controllers.dtos.CreateUserDto;
import com.fincontrol.demo.controllers.dtos.UpdateUserDto;
import com.fincontrol.demo.models.Role;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.repositories.RoleRepository;
import com.fincontrol.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/usuario")
    public List<Usuario> obterTodos(){return this.usuarioService.obterTodos();}

    @GetMapping(value = "/usuario/{cpf}")
    public ResponseEntity<Usuario> obterPorCpf(@PathVariable String cpf){
        Usuario usuario = usuarioService.obterPorCpf(cpf);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<Usuario> criarPaciente(@RequestBody CreateUserDto createUserDto){

            var roleBasic = roleRepository.findByName(Role.Values.BASIC.name());

        var usuario = new Usuario();
            usuario.setCpf(createUserDto.cpf());
            usuario.setNome(createUserDto.nome());
            usuario.setEmail(createUserDto.email());
            usuario.setPassword(passwordEncoder.encode(createUserDto.password()));
            usuario.setRoles(Set.of(roleBasic));

            this.usuarioService.criar(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping(value = "/usuario/{cpf}")
    public ResponseEntity<Usuario> atualizar(@PathVariable String cpf, @RequestBody UpdateUserDto updateUserDto) {
        usuarioService.atualizarUserByCpf(cpf, updateUserDto);
        return ResponseEntity.noContent().build();
    }
}
