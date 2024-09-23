package com.galli.contas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private double valor;
    private String descricao;
    private String situacao;

    public void marcarComoPago() {
        this.situacao = "PAGO";
        this.dataPagamento = LocalDate.now();
    }
}