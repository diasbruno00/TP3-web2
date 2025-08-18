package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.converter.InvestimentsConverter;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.InvestmentsDomain;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.useCase.CreateInvestimentsUseCase;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.domain.useCase.EditInvestimentsUseCase;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.*;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.models.InvestimentsModel;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.repositories.IInvestimentsRepository;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.awesomeApi.AwesomeApi;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.brapiApi.BrapiClient;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.AwesomeApiCriptoResponseDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.QuoteResponseDTO;
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

    public InvestimentsSummaryDTO investimentsSummary(){
        // Calcular o total investido, a contagem de ativos e o total por tipo
        Float totalInvested = investimentsRepository.sumAllInitialInvestments();
        Long  assetCount = investimentsRepository.countAllInvestments();
        List<Object[]> totalByType = investimentsRepository.sumInitialInvestmentByType();

        return new InvestimentsSummaryDTO(totalInvested, assetCount, totalByType);
    }


    public InvestimentsDTO createInvestiment(CreateInvestimentsDTO investiment) {

        InvestmentsDomain investmentsDomain = InvestimentsConverter.toInvestmentsDomain(investiment);

        if(investmentsDomain.getType()== EnumInvestimentsType.ACAO) {

            //  Obter o preço atual da ação usando o BrapiClient
            QuoteResponseDTO response = brapiClient.getQuote(investmentsDomain.getSymbol());

            // obter o preço atual da ação
            Double precoAtual = response.getResults().get(0).getRegularMarketPrice();

            investmentsDomain.setPurchasePrice(precoAtual.floatValue());

        }else if (investmentsDomain.getType() == EnumInvestimentsType.CRIPTO){

            // Obter o preço atual da criptomoeda usando o AwesomeApiCriptoClient

            AwesomeApiCriptoResponseDTO response = awesomeApi.getCripto(investmentsDomain.getSymbol());
            System.out.print(response);

            // Obter o preço de compra (bid) da criptomoeda
            String symbol = investmentsDomain.getSymbol() + "BRL";
            AwesomeApiCriptoResponseDTO.CriptoBRL cripto = response.getQuotes().get(symbol);

            if (cripto != null) {
                String precoCompra = cripto.getBid();
                investmentsDomain.setPurchasePrice(Float.parseFloat(precoCompra));
            }

        } //else {
            //throw new RuntimeException("Tipo de investimento não suportado: " + investmentsDomain.getType());
        //}

        CreateInvestimentsUseCase createInvestimentsUseCase = new CreateInvestimentsUseCase(investmentsDomain);
        createInvestimentsUseCase.validateInvestments();
        createInvestimentsUseCase.calculateInitialInvestment();

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

    public EditInvestimentsDTO updateInvestimentById(EditInvestimentsDTO investiment) {

        Optional<InvestimentsModel> investimentOptional = investimentsRepository.findById(investiment.id());

        if(investimentOptional.isEmpty()){
            throw new RuntimeException("Investimento não encontrado com o ID: " + investiment.id());
        }

        // validar os dados do investimento antes de atualizar
        InvestmentsDomain investmentsDomain = InvestimentsConverter.toEditInvestmentsDomain(investiment);
        System.out.print("InvestmentsDomain: " + investmentsDomain);

        EditInvestimentsUseCase editInvestimentsUseCase = new EditInvestimentsUseCase(investmentsDomain);
        editInvestimentsUseCase.validateEditInvestments();


        InvestimentsModel investimentsModel = investimentOptional.get();
        investimentsModel.setQuantity(investiment.quantity());
        investimentsModel.setPurchasePrice(investiment.purchasePrice());
        investimentsModel.setSalePrice(investiment.salePrice());

        return InvestimentsConverter.toEditInvestmentsModel(investimentsRepository.save(investimentsModel));

    }

    public InvestimentsDTO CalculateProfitOrLoss(UUID id) {

        Optional<InvestimentsModel> investimentOptional = investimentsRepository.findById(id);
        if (investimentOptional.isEmpty()) {
            throw new RuntimeException("Investimento não encontrado com o ID: " + id);
        }

        InvestimentsModel investimentsModel = investimentOptional.get();
        InvestmentsDomain investmentsDomain = InvestimentsConverter.toInvestmentsDomain(investimentsModel);

        EditInvestimentsUseCase editInvestimentsDTO = new EditInvestimentsUseCase(investmentsDomain);
        editInvestimentsDTO.CalculateProfitOrLoss();

        InvestimentsModel investimentsModelAtualizado = InvestimentsConverter.toInvestimentsModel(investmentsDomain);
        investimentsModelAtualizado.setFinalInvestment(investmentsDomain.getFinalInvestment());
        investimentsModelAtualizado.setStatusProfitOrLoss(investmentsDomain.getStatusProfitOrLoss());
        investimentsModelAtualizado.setSalePrice(investmentsDomain.getSalePrice());
        investimentsModelAtualizado.setProfit(investmentsDomain.getProfit());

        return InvestimentsConverter.toInvestimentsDTO(investimentsRepository.save(investimentsModelAtualizado));

    }




}
