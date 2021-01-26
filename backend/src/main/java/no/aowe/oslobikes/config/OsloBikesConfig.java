package no.aowe.oslobikes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OsloBikesConfig {
    @Value("${identifier}")
    private String identifier;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://gbfs.urbansharing.com/oslobysykkel.no")
                .defaultHeader("Client-Identifier", identifier)
                .build();
    }
}
