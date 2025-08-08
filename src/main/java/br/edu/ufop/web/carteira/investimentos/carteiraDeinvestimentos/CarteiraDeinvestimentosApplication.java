package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarteiraDeinvestimentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteiraDeinvestimentosApplication.class, args);
	}

}
