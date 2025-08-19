package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.useCase;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class EditInvestimentsUseCase {

    InvestmentsDomain investmentsDomain;

    public void validateEditInvestments() {
        validateQuantity();
        validatePurchasePrice();
    }

    public void CalculateProfitOrLoss(Integer quantitySell, Float salePriceParam) {

         Float inicialInvestment = investmentsDomain.getInitialInvestment();
         investmentsDomain.setSalePrice(salePriceParam);

         Float salePrice = investmentsDomain.getSalePrice();

            if (salePrice != null && quantitySell <= investmentsDomain.getQuantity() && quantitySell > 0) {

                Float profitOrLoss = salePrice * quantitySell;

                investmentsDomain.setQuantity(investmentsDomain.getQuantity() - quantitySell);

                if (profitOrLoss >= inicialInvestment) {

                    investmentsDomain.setStatusProfitOrLoss("Profit");
                }else{
                    investmentsDomain.setStatusProfitOrLoss("Loss");
                }

                investmentsDomain.setProfit(profitOrLoss - inicialInvestment);
                investmentsDomain.setFinalInvestment(profitOrLoss);

            } else {
                investmentsDomain.setStatusProfitOrLoss("No Sale Price Provided");
                investmentsDomain.setFinalInvestment(0f);
                throw new IllegalArgumentException("Sale price must be provided and quantity sold must not exceed current quantity");
            }
    }

    private void validateQuantity() {
        if (investmentsDomain.getQuantity() == null || investmentsDomain.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    private void validatePurchasePrice() {
        if (investmentsDomain.getPurchasePrice() == null || investmentsDomain.getPurchasePrice() <= 0) {
            throw new IllegalArgumentException("Purchase price must be greater than zero");
        }
    }

    public void validateSalePrice() {
        if (investmentsDomain.getSalePrice() != null && investmentsDomain.getSalePrice() <= 0) {
            throw new IllegalArgumentException("Sale price must be greater than zero if provided");
        }
    }

    public void updateInitialInvestment() {
        this.investmentsDomain.setInitialInvestment(this.investmentsDomain.getQuantity() * this.investmentsDomain.getPurchasePrice());
    }


}
