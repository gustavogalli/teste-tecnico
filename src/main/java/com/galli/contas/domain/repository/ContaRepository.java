package com.galli.contas.domain.repository;

import com.galli.contas.domain.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :dataInicial AND :dataFinal AND c.situacao = 'PAGO'")
    BigDecimal calcularValorTotalPago(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);

    Page<Conta> findByDataVencimentoAndDescricaoContainingIgnoreCase(LocalDate dataVencimento, String descricao, Pageable pageable);

    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.dataPagamento BETWEEN :start AND :end AND c.situacao = 'PAGO'")
    BigDecimal findValorTotalPagoByDataPagamentoBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}