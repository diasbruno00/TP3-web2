package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IInvestimentsRepository extends JpaRepository<InvestimentsModel, UUID> {
}
