package io.dddbyexamples.delivery.planning.plan;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static java.time.Duration.ofMillis;
import static java.util.Collections.emptyList;

class RestCurrentDemandProvider implements CurrentDemandProvider {

    private final RestTemplate restTemplate;
    private final String demandsUrl;
    private final int retries;
    private final long waitDuration;

    RestCurrentDemandProvider(RestTemplate restTemplate,
                              @Value("${rest.url.demamds}") String demandsUrl,
                              @Value("${rest.retries}") int retries,
                              @Value("${rest.timeout}") long waitDuration) {
        this.restTemplate = restTemplate;
        this.demandsUrl = demandsUrl;
        this.retries = retries;
        this.waitDuration = waitDuration;
    }

    @Override
    public List<CurrentDemand> getByDate(LocalDate date) {

        RetryConfig config = RetryConfig.custom()
                .maxAttempts(retries)
                .waitDuration(ofMillis(waitDuration))
                .build();
        Retry retry = Retry.of("demands", config);

        CheckedFunction0<Demands> retryableSupplier = Retry.decorateCheckedSupplier(retry, () -> fetchDemands(date));

        Try<Demands> result = Try.of(retryableSupplier).recover((throwable) -> Demands.emptyDemands);

        return result.getOrElse(Demands.emptyDemands).getDemands();
    }

    private Demands fetchDemands(LocalDate date) {
        return restTemplate.getForObject(demandsUrl, Demands.class, date);
    }

    @Getter
    @AllArgsConstructor
    private static class Demands {
        static Demands emptyDemands = new Demands(emptyList());

        private List<CurrentDemand> demands;
    }
}
