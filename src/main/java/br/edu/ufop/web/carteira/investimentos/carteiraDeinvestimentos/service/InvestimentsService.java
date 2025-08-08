package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.converter.InvestimentsConverter;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.IInvestimentsRepository;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.awesomeApi.AwesomeApi;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.brapiApi.BrapiClient;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.AwesomeApiCriptoResponse;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.QuoteResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestimentsService {

    private final IInvestimentsRepository investimentsRepository;
    private final BrapiClient brapiClient;
    private final AwesomeApi  awesomeApi;


    public InvestimentsDTO createInvestiment(CreateInvestimentsDTO investiment) {

        InvestmentsDomain investmentsDomain = InvestimentsConverter.toInvestmentsDomain(investiment);

        if(investmentsDomain.getType()== EnumInvestimentsType.ACAO) {

            //  Obter o preço atual da ação usando o BrapiClient
            QuoteResponse response = brapiClient.getQuote(investmentsDomain.getSymbol());

            // obter o preço atual da ação
            Double precoAtual = response.getResults().get(0).getRegularMarketPrice();

            investmentsDomain.setPurchasePrice(precoAtual.floatValue());

        }else if (investmentsDomain.getType() == EnumInvestimentsType.CRIPTO){

            // Obter o preço atual da criptomoeda usando o AwesomeApiCriptoClient

            AwesomeApiCriptoResponse response = awesomeApi.getCripto(investmentsDomain.getSymbol());
            System.out.print(response);

            // Obter o preço de compra (bid) da criptomoeda
            String symbol = investmentsDomain.getSymbol() + "BRL";
            AwesomeApiCriptoResponse.CriptoBRL cripto = response.getQuotes().get(symbol);

            if (cripto != null) {
                String precoCompra = cripto.getBid();
                investmentsDomain.setPurchasePrice(Float.parseFloat(precoCompra));
            }

        } else {
            throw new RuntimeException("Tipo de investimento não suportado: " + investmentsDomain.getType());
        }



        InvestimentsModel investimentsModel = InvestimentsConverter.toInvestimentsModel(investmentsDomain);


        return InvestimentsConverter.toInvestimentsDTO(investimentsRepository.save(investimentsModel));


    }

    public List<InvestimentsDTO> getAllInvestments() {
        return investimentsRepository.findAll().stream()
                .map(InvestimentsConverter::toInvestimentsDTO)
                .collect(Collectors.toList());
    }


    public List<InvestimentsDTO> getAllByTypeInvestiments(EnumInvestimentsType type) {

        List<InvestimentsModel> investimentsModel = investimentsRepository.findAllByType(type);


        return investimentsModel.stream().map(InvestimentsConverter::toInvestimentsDTO).toList();


    }

    public InvestimentsDTO deleteInvestimentById(UUID id) {
        Optional<InvestimentsModel> investimentOptional = investimentsRepository.findById(id);
        if (investimentOptional.isPresent()) {
            investimentsRepository.deleteById(id);
            return InvestimentsConverter.toInvestimentsDTO(investimentOptional.get());
        } else {
            throw new RuntimeException("Investimento não encontrado com o ID: " + id);
        }
    }
}
