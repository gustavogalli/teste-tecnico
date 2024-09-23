package com.galli.contas.domain.service;

import com.galli.contas.domain.model.Conta;
import com.galli.contas.domain.repository.ContaRepository;

public class DomainContaService {

    private final ContaRepository contaRepository;

    public DomainContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void marcarContaComoPaga(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.marcarComoPago();
        contaRepository.save(conta);
    }
}
