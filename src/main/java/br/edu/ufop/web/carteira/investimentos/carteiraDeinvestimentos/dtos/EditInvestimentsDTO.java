package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

public record EditInvestimentsDTO(
        String symbol,
        Integer quantity,
        Float purchasePrice,
        Float initialInvestment
) {

}
