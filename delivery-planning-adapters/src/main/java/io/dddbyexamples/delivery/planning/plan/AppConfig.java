package io.dddbyexamples.delivery.planning.plan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${rest.url.demands}") String demandsUrl;
    @Value("${rest.retries}") int retries;
    @Value("${rest.timeout}") long waitDuration;

    @Bean
    public PlanCompletenessProvider planCompletenessProvider() {
        return new PlanCompletenessProviderAdapter(currentDemandProvider());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrentDemandProvider currentDemandProvider() {
        return new RestCurrentDemandProvider(restTemplate(), demandsUrl, retries, waitDuration);
    }
}
