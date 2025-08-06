package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.converter.InvestimentsConverter;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.IInvestimentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestimentsService {

    private final IInvestimentsRepository investimentsRepository;


    public CreateInvestimentsDTO createInvestiment(CreateInvestimentsDTO investiment) {

        InvestmentsDomain investmentsDomain = InvestimentsConverter.toInvestmentsDomain(investiment);
        // espaço para fazer validações ou lógicas adicionais


        InvestimentsModel investimentsModel = InvestimentsConverter.toInvestimentsModel(investmentsDomain);


        return InvestimentsConverter.toInvestimentsDTO(investimentsRepository.save(investimentsModel));


    }

    public List<InvestimentsDTO> getAllInvestments() {
        return investimentsRepository.findAll().stream()
                .map(InvestimentsConverter::toAllInvestimentsDTO)
                .collect(Collectors.toList());
    }






}
