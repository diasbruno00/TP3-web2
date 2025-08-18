package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response;

import lombok.Data;
import java.util.List;

@Data
public class QuoteResponseDTO {
    private List<Stock> results;

    @Data
    public static class Stock {
        private String symbol;
        private String shortName;
        private String longName;
        private String currency;
        private Double regularMarketPrice;
        private Double regularMarketDayHigh;
        private Double regularMarketDayLow;
        private Double regularMarketChange;
        private Double regularMarketChangePercent;
        private String regularMarketTime;
        private Long marketCap;
        private Long regularMarketVolume;
        private String logourl;
    }
}