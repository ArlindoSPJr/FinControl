package com.fincontrol.demo.controllers.dtos;

import java.time.LocalDate;

public record UpdateTransacaoDto(LocalDate data, String descricao, double valor, String destinatario) {
}
