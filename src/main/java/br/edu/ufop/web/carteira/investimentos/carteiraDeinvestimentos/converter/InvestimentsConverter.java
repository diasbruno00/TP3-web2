package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.converter;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.EditInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvestimentsConverter {


    public static InvestmentsDomain toInvestmentsDomain(CreateInvestimentsDTO investiments) {
        return InvestmentsDomain.builder()
                .type(investiments.type())
                .symbol(investiments.symbol())
                .quantity(investiments.quantity())
                .build();
    }

    public static InvestmentsDomain toInvestmentsDomain(EditInvestimentsDTO investments) {
        InvestmentsDomain investmentsDomain = new InvestmentsDomain();
        investmentsDomain.setSymbol(investments.symbol());
        investmentsDomain.setQuantity(investments.quantity());
        investmentsDomain.setPurchasePrice(investments.purchasePrice());
        investmentsDomain.setInitialInvestment(investments.initialInvestment());
        investmentsDomain.calculateInitialInvestment();
        return investmentsDomain;
    }


    public static InvestimentsModel toInvestimentsModel(InvestmentsDomain investments) {
        return InvestimentsModel.builder()
                .id(investments.getId())
                .type(investments.getType())
                .symbol(investments.getSymbol())
                .quantity(investments.getQuantity())
                .purchasePrice(investments.getPurchasePrice())
                .purchaseDate(investments.getPurchaseDate())
                .initialInvestment(investments.getInitialInvestment())
                .build();
    }



    public static InvestimentsDTO toInvestimentsDTO(InvestimentsModel investments) {
        return new InvestimentsDTO(
                investments.getId(),
                investments.getType(),
                investments.getSymbol(),
                investments.getQuantity(),
                investments.getPurchasePrice(),
                investments.getPurchaseDate(),
                investments.getInitialInvestment()

        );
    }



}
