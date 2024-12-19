package com.fincontrol.demo.controllers;


import com.fincontrol.demo.controllers.dtos.CreateInvestimentoDto;
import com.fincontrol.demo.controllers.dtos.UpdateInvestimentoDto;
import com.fincontrol.demo.models.Investimento;
import com.fincontrol.demo.models.Logger;
import com.fincontrol.demo.services.InvestimentoService;
import com.fincontrol.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class InvestimentoController {

    @Autowired
    private InvestimentoService investimentoService;

    @GetMapping(value = "/investimento")
    public List<Investimento> listaInvestimento() {
        return investimentoService.obterInvestimentos();
    }

    @GetMapping(value = "/investimento/{cpf}")
    public List<Investimento> listaInvestimentoUser(@PathVariable String cpf) {
        return investimentoService.obterInvestimentosPorUsuarioCpf(cpf);
    }



    @GetMapping(value = "/investimento/{id}")
    public Investimento obterInvestimento(@PathVariable Long id) {
        return investimentoService.obterInvestimentoPorId(id);
    }

    @PostMapping(value = "/investimento")
    public ResponseEntity<Investimento> criarInvestimento(@RequestBody CreateInvestimentoDto createInvestimentoDto) {
        var investimento = new Investimento();
            investimento.setMinimo(createInvestimentoDto.valorMinimo());
            investimento.setTipoInvestimento(createInvestimentoDto.tipoInvestimento());
            investimento.setPreco(createInvestimentoDto.preco());

            investimentoService.criarInvestimento(investimento);

            investimentoService.adicionarObserver(investimento, Logger.getInstance());

            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/investimento/{id}")
    public ResponseEntity<Investimento> alterarValor(@PathVariable Long id, @RequestBody UpdateInvestimentoDto updateInvestimentoDto) {
        Investimento investimento = investimentoService.obterInvestimentoPorId(id);
        investimentoService.alterarValor(investimento, updateInvestimentoDto.novoPreco());

        return ResponseEntity.ok(investimento);

    }

}
