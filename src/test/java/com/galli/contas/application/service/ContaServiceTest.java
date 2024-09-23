package com.galli.contas.application.service;

import com.galli.contas.domain.model.Conta;
import com.galli.contas.domain.repository.ContaRepository;
import com.galli.contas.interfaces.dto.ContaDTO;
import com.galli.contas.interfaces.mapper.ContaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ContaMapper contaMapper;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarConta() {
        ContaDTO contaDTO = new ContaDTO();
        Conta conta = new Conta();
        Conta savedConta = new Conta();

        when(contaMapper.toEntity(contaDTO)).thenReturn(conta);
        when(contaRepository.save(conta)).thenReturn(savedConta);
        when(contaMapper.toDTO(savedConta)).thenReturn(contaDTO);

        ContaDTO result = contaService.cadastrarConta(contaDTO);

        assertNotNull(result);
        verify(contaRepository, times(1)).save(conta);
    }

    @Test
    void testAtualizarConta() {
        Long id = 1L;
        ContaDTO contaDTO = new ContaDTO();
        Conta conta = new Conta();
        Conta updatedConta = new Conta();

        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));
        when(contaMapper.toEntity(contaDTO)).thenReturn(updatedConta);
        when(contaRepository.save(updatedConta)).thenReturn(updatedConta);
        when(contaMapper.toDTO(updatedConta)).thenReturn(contaDTO);

        ContaDTO result = contaService.atualizarConta(id, contaDTO);

        assertNotNull(result);
        verify(contaRepository, times(1)).findById(id);
        verify(contaRepository, times(1)).save(updatedConta);
    }

    @Test
    void testAlterarSituacao() {
        Long id = 1L;
        String novaSituacao = "PAGO";
        Conta conta = new Conta();

        when(contaRepository.findById(id)).thenReturn(Optional.of(conta));

        contaService.alterarSituacao(id, novaSituacao);

        assertEquals(novaSituacao, conta.getSituacao());
        verify(contaRepository, times(1)).save(conta);
    }

    @Test
    void testObterValorTotalPago() {
        LocalDate dataInicial = LocalDate.parse("2024-09-01");
        LocalDate dataFinal = LocalDate.parse("2024-09-30");
        double valorTotal = 1000.00;

        when(contaRepository.calcularValorTotalPago(dataInicial, dataFinal))
                .thenReturn(BigDecimal.valueOf(valorTotal));

        BigDecimal result = contaService.obterValorTotalPago(dataInicial, dataFinal);

        assertEquals(valorTotal, result);
        verify(contaRepository, times(1)).calcularValorTotalPago(dataInicial, dataFinal);
    }
}
