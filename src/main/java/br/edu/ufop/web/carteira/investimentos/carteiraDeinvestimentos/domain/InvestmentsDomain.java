package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InvestmentsDomain {
    private UUID id;
    private EnumInvestimentsType type;
    private String symbol ;
    private Integer quantity;
    private Float purchasePrice;
    private LocalDateTime purchaseDate;
    private Float initialInvestment;



}
