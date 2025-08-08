package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.awesomeApi;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response.AwesomeApiCriptoResponse;

@FeignClient(
    name = "awesomeApiClient",
    url = "https://economia.awesomeapi.com.br"
)
public interface AwesomeApi {

    @GetMapping("/json/last/{cripto}-BRL")
    AwesomeApiCriptoResponse getCripto(@PathVariable("cripto") String cripto);
}

