package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import java.util.UUID;

public record EditInvestimentsDTO(
        UUID id,
        Integer quantity,
        Float purchasePrice
) {

}
