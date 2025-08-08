package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class BrapiFeignClientConfiguration {
    @Value("${brapi.api.token}")
    private String apiToken;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Authorization", "Bearer " + apiToken);
            }
        };
    }
}

