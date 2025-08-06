package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;

import java.time.LocalDateTime;
import java.util.UUID;

public record InvestimentsDTO(
        UUID id,
        EnumInvestimentsType type,
        String symbol ,
        Integer quantity,
        Float purchasePrice,
        LocalDateTime purchaseDate
) {
}
