package com.fincontrol.demo.controllers;

import com.fincontrol.demo.controllers.dtos.CreateTransacaoDto;
import com.fincontrol.demo.controllers.dtos.UpdateTransacaoDto;
import com.fincontrol.demo.models.Transacao;
import com.fincontrol.demo.models.Usuario;
import com.fincontrol.demo.services.TransacaoService;
import com.fincontrol.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/transacoes") // Define um prefixo comum para todos os endpoints
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final UsuarioService usuarioService;

    public TransacaoController(TransacaoService transacaoService, UsuarioService usuarioService) {
        this.transacaoService = transacaoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping // Endpoint: GET /transacoes
    public List<Transacao> obterTransacoes() {
        return transacaoService.obterTodas();
    }

    @GetMapping("/{id}") // Endpoint: GET /transacoes/{id}
    public Transacao obterTransacao(@PathVariable Long id) {
        return transacaoService.obterPorId(id);
    }

    @GetMapping("/data/{data}") // Endpoint: GET /transacoes/data/{data}
    public List<Transacao> obterTransacoesData(@PathVariable LocalDate data) {
        return transacaoService.obterPorData(data);
    }

    @GetMapping("/cpf/{cpf}") // Endpoint: GET /transacoes/cpf/{cpf}
    public List<Transacao> obterTransacoesCpf(@PathVariable String cpf) {
        return transacaoService.obterPorUserCpf(cpf);
    }

    @PostMapping("/{id}") // Endpoint: POST /transacoes/{id}
    public ResponseEntity<Transacao> adicionarTransacao(@PathVariable Long id,
                                                        @RequestBody CreateTransacaoDto createTransacaoDto) {

        Usuario usuario = usuarioService.obterPorId(id);

        Transacao transacao = new Transacao();
        transacao.setData(createTransacaoDto.data());
        transacao.setValor(createTransacaoDto.valor());
        transacao.setUsuario(usuario);
        transacao.setDestinatario(createTransacaoDto.destinatario());
        transacao.setDescricao(createTransacaoDto.descricao());

        transacaoService.criarTransacao(transacao);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}") // Endpoint: PUT /transacoes/{id}
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long id,
                                                        @RequestBody UpdateTransacaoDto updateTransacaoDto) {
        System.out.println("Recebido no servidor: " + updateTransacaoDto);
        transacaoService.atualizarById(id, updateTransacaoDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transacao> delearTransacao(@PathVariable Long id) {
        Transacao transacao = transacaoService.obterPorId(id);
        transacaoService.excluirTransacao(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
