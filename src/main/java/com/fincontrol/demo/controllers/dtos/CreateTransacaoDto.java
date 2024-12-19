package com.fincontrol.demo.controllers.dtos;

import com.fincontrol.demo.models.Usuario;

import java.time.LocalDate;

public record CreateTransacaoDto(LocalDate data, String descricao, double valor, String destinatario, Usuario usuario) {
}
