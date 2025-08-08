package br.edu.ufop.web.carteira.investimentos.carteiraDeinvestimentos.service.clients.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class AwesomeApiCriptoResponse {

    private Map<String, CriptoBRL> quotes = new HashMap<>();

    @JsonAnySetter
    public void addQuote(String key, CriptoBRL value) {
        quotes.put(key, value);
    }

    @Data
    public static class CriptoBRL {
        private String code;
        private String codein;
        private String name;
        private String high;
        private String low;
        private String varBid;
        private String pctChange;
        private String bid;
        private String ask;
        private String timestamp;
        private String create_date;
    }
}