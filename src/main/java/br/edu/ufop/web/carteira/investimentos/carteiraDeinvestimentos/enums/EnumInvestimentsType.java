package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumInvestimentsType {

    ACAO(1, "Ações"),
    CRIPTO(2, "Cripto"),
    FUNDOS(3, "Fundos"),
    RENDIMENTO_FIXO(4, "Rendimento Fixo");

    private Integer id;
    private String description;
}
