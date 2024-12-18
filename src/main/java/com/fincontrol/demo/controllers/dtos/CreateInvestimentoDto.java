package com.fincontrol.demo.controllers.dtos;

import com.fincontrol.demo.models.TipoInvestimento;

public record CreateInvestimentoDto(double preco, TipoInvestimento tipoInvestimento, double valorMinimo) {
}
