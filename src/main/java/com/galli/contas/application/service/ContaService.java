package com.galli.contas.application.service;

import com.galli.contas.domain.model.Conta;
import com.galli.contas.domain.repository.ContaRepository;
import com.galli.contas.interfaces.dto.ContaDTO;
import com.galli.contas.interfaces.mapper.ContaMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    public ContaDTO cadastrarConta(ContaDTO contaDTO) {
        Conta conta = contaMapper.toEntity(contaDTO);
        Conta savedConta = contaRepository.save(conta);
        return contaMapper.toDTO(savedConta);
    }

    public ContaDTO atualizarConta(Long id, ContaDTO contaDTO) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            Conta updatedConta = contaMapper.toEntity(contaDTO);
            updatedConta.setId(id);
            Conta savedConta = contaRepository.save(updatedConta);
            return contaMapper.toDTO(savedConta);
        } else {
            throw new RuntimeException("Conta não encontrada!");
        }
    }

    public void alterarSituacao(Long id, String novaSituacao) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        conta.setSituacao(novaSituacao);
        contaRepository.save(conta);
    }

    public List<ContaDTO> obterContas(String descricao, String dataVencimento) {
        return contaMapper.toDTOList(contaRepository.findAll());
    }

    public BigDecimal obterValorTotalPago(LocalDate dataInicial, LocalDate dataFinal) {
        return contaRepository.calcularValorTotalPago(dataInicial, dataFinal);
    }

    public void importarContasCSV(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim()
                    .parse(reader);

            for (CSVRecord record : records) {
                ContaDTO contaDTO = new ContaDTO();

                contaDTO.setDataVencimento(LocalDate.parse(record.get("data_vencimento")));
                contaDTO.setDataPagamento(record.get("data_pagamento").isEmpty() ? null : LocalDate.parse(record.get("data_pagamento")));
                contaDTO.setValor(Double.parseDouble(record.get("valor")));
                contaDTO.setDescricao(record.get("descricao"));
                contaDTO.setSituacao(record.get("situacao").toUpperCase());

                cadastrarConta(contaDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo CSV: " + e.getMessage());
        }
    }
}
