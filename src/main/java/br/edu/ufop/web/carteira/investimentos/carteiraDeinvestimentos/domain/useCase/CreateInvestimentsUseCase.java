package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.useCase;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
public class CreateInvestimentsUseCase {

    InvestmentsDomain investmentsDomain;

    public void validateInvestments() {
        validateEnumInvestimentsType();
        validateSymbol();
        validateQuantity();
        validatePurchasePrice();
    }
    public void calculateInitialInvestment() {
        this.investmentsDomain.setInitialInvestment( this.investmentsDomain.getQuantity() * this.investmentsDomain.getPurchasePrice());
    }

    public void validateEnumInvestimentsType() {
        if (this.investmentsDomain.getType() == null) {
            throw new IllegalArgumentException("Investment type cannot be null");
        }
    }
    public void validateSymbol() {
        if (this.investmentsDomain.getSymbol() == null || this.investmentsDomain.getSymbol().isBlank()) {
            throw new IllegalArgumentException("Symbol cannot be null or blank");
        }
    }
    public void validateQuantity() {
        if (this.investmentsDomain.getQuantity() == null || this.investmentsDomain.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
    public void validatePurchasePrice() {
        if (this.investmentsDomain.getPurchasePrice() == null || this.investmentsDomain.getPurchasePrice() <= 0) {
            throw new IllegalArgumentException("Purchase price must be greater than zero");
        }
    }



}
