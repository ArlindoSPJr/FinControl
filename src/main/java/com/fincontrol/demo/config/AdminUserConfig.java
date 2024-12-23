package com.fincontrol.demo.config;


import com.fincontrol.demo.models.Role;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.repositories.RoleRepository;
import com.fincontrol.demo.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UsuarioRepository usuarioRepository;

    public AdminUserConfig(RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           UsuarioRepository usuarioRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Verifica se o papel ADMIN existe; se não, cria e salva
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        if (roleAdmin == null) {
            roleAdmin = new Role();
            roleAdmin.setName(Role.Values.ADMIN.name());
            roleRepository.save(roleAdmin);
        }

        // Procura um usuário admin
        var userAdmin = usuarioRepository.findByNome("admin");

        Role finalRoleAdmin = roleAdmin;
        userAdmin.ifPresentOrElse(
                usuario -> System.out.println("admin já existe"),
                () -> {
                    var usuario = new Usuario();
                    usuario.setNome("admin");
                    usuario.setPassword(passwordEncoder.encode("123"));
                    usuario.setEmail("admin@admin.com");

                    // Associa o papel ADMIN ao novo usuário admin
                    usuario.setRoles(Set.of(finalRoleAdmin));
                    usuarioRepository.save(usuario);
                    System.out.println("Usuário admin criado com sucesso.");
                }
        );
    }


}
