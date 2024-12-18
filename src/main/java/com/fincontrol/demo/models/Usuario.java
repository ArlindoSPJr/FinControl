package com.fincontrol.demo.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_cpf", unique = true)
    private String cpf;

    @Column(name = "user_nome", nullable = false)
    private String nome;

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "users_transacoes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transacao_id")
    )
    private Set<Transacao> transacaos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_despesa",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "despesa_id")
    )
    private Set<Despesa> despesas;

    public Usuario() {}

    public Usuario(String cpf, String nome, String email, String password) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

}
