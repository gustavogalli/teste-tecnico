package com.galli.contas.interfaces.mapper;

import com.galli.contas.domain.model.Conta;
import com.galli.contas.interfaces.dto.ContaDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContaMapper {

    public ContaDTO toDTO(Conta conta) {
        ContaDTO dto = new ContaDTO();
        dto.setId(conta.getId());
        dto.setDataVencimento(conta.getDataVencimento());
        dto.setDataPagamento(conta.getDataPagamento());
        dto.setValor(conta.getValor());
        dto.setDescricao(conta.getDescricao());
        dto.setSituacao(conta.getSituacao());
        return dto;
    }

    public Conta toEntity(ContaDTO dto) {
        Conta conta = new Conta();
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setValor(dto.getValor());
        conta.setDescricao(dto.getDescricao());
        conta.setSituacao(dto.getSituacao());
        return conta;
    }

    public List<ContaDTO> toDTOList(List<Conta> contas) {
        return contas.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
