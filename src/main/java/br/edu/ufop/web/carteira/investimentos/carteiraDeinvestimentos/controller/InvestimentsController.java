package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.controller;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.CreateInvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos.InvestimentsDTO;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums.EnumInvestimentsType;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.InvestimentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investments")
@AllArgsConstructor
public class InvestimentsController {

    private final InvestimentsService investimentsService;


    @PostMapping
    public ResponseEntity<InvestimentsDTO> createInvestiment(@RequestBody CreateInvestimentsDTO investiment) {
        try{
            InvestimentsDTO createdInvestiment = investimentsService.createInvestiment(investiment);

            return ResponseEntity.ok(createdInvestiment);

        } catch (Exception e) {
            System.out.println("Erro ao criar investimento: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<InvestimentsDTO>> getAllInvestments() {
        try {
            return ResponseEntity.ok(investimentsService.getAllInvestments());
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os investimentos: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/type={type}")
    public ResponseEntity<List<InvestimentsDTO>> getAllByTypeInvestiments(@PathVariable EnumInvestimentsType type) {
        try {
            return ResponseEntity.ok(investimentsService.getAllByTypeInvestiments(type));
        } catch (Exception e) {
            System.out.println("Erro ao buscar investimentos por tipo: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/id={id}")
    public ResponseEntity<InvestimentsDTO> deleteInvestimentById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(investimentsService.deleteInvestimentById(id));
        } catch (Exception e) {
            System.out.println("Erro ao deletar investimento: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }



}
