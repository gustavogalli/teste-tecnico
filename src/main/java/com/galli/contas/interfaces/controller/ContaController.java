package com.galli.contas.interfaces.controller;

import com.galli.contas.application.service.ContaService;
import com.galli.contas.interfaces.dto.ContaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> cadastrarConta(@RequestBody ContaDTO contaDTO) {
        ContaDTO novaConta = contaService.cadastrarConta(contaDTO);
        return ResponseEntity.ok(novaConta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaDTO> atualizarConta(@PathVariable Long id, @RequestBody ContaDTO contaDTO) {
        ContaDTO contaAtualizada = contaService.atualizarConta(id, contaDTO);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<Void> alterarSituacao(@PathVariable Long id, @RequestParam String situacao) {
        contaService.alterarSituacao(id, situacao);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> obterContas(@RequestParam(required = false) String descricao,
                                                      @RequestParam(required = false) String dataVencimento) {
        return ResponseEntity.ok(contaService.obterContas(descricao, dataVencimento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> obterContaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importarContasCSV(@RequestParam("file") MultipartFile file) {
        contaService.importarContasCSV(file);
        return ResponseEntity.ok().build();
    }
}
