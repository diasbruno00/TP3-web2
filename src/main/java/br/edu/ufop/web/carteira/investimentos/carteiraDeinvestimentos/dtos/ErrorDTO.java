package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.dtos;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime timestamp
) {
}
