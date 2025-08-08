package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;

public record CreateInvestimentsDTO(
        EnumInvestimentsType type,
        String symbol,
        Integer quantity
) {
}
