package com.galli.contas.interfaces.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ContaDTO {
    private Long id;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valor;
    private String descricao;
    private String situacao;
}