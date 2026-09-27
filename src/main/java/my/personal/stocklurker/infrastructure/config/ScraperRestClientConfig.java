package my.personal.stocklurker.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class ScraperRestClientConfig {

    @Bean("scraperRestClient")
    public RestClient scraperRestClient(RestClient.Builder builder) {
        return builder
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36");
                    headers.add(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
                    headers.add(HttpHeaders.ACCEPT_LANGUAGE, "es-ES,es;q=0.9,en;q=0.8");
                    headers.add(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
                })
                .build();
    }
}