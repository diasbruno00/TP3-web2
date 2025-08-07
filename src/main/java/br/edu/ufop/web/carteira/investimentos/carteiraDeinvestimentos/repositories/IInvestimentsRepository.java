package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IInvestimentsRepository extends JpaRepository<InvestimentsModel, UUID> {

 List<InvestimentsModel> findAllByType(EnumInvestimentsType type);
}
