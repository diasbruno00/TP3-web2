package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import java.util.List;

public record InvestimentsSummaryDTO(
        Float totalInvested,
        Long assetCount,
        List<Object[]> totalByType

) {
}
