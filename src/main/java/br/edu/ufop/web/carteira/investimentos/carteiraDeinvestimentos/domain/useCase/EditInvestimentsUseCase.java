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
}
