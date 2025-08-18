package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import java.util.UUID;

public record SaleInvestimentsDTO(
        UUID id,
        Float salePrice,
        Integer quantity
) {
}
