package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.controller;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.InvestimentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/investments")
@AllArgsConstructor
public class InvestimentsController {

    private final InvestimentsService investimentsService;


    @PostMapping
    public ResponseEntity<CreateInvestimentsDTO> createInvestiment(@RequestBody CreateInvestimentsDTO investiment) {
        try{
            CreateInvestimentsDTO createdInvestiment = investimentsService.createInvestiment(investiment);
            return ResponseEntity.ok(createdInvestiment);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
