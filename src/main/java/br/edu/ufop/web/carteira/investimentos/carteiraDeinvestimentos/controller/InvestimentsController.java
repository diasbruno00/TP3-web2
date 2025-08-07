package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.controller;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.InvestimentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<InvestimentsDTO>> getAllInvestments() {
        try {
            return ResponseEntity.ok(investimentsService.getAllInvestments());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/type={type}")
    public ResponseEntity<List<InvestimentsDTO>> getAllByTypeInvestiments(@PathVariable EnumInvestimentsType type) {
        System.out.printf("Enum recebido: %s%n", type);
        try {
            return ResponseEntity.ok(investimentsService.getAllByTypeInvestiments(type));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }






}
