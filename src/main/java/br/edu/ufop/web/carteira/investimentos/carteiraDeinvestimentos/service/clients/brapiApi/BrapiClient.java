package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.brapiApi;

import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.QuoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "brapiClient",
        url = "https://brapi.dev/api",
        configuration = BrapiFeignClientConfiguration.class // Adiciona a configuração do interceptor ou seja o token
)

public interface BrapiClient {
    @GetMapping("/quote/{ticker}")
    QuoteResponse getQuote(@PathVariable("ticker") String ticker);


}
