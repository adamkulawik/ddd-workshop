package io.dddbyexamples.delivery.planning.plan;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;

class RestCurrentDemandProvider implements CurrentDemandProvider {

    private final RestTemplate restTemplate;

    RestCurrentDemandProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CurrentDemand> getByDate(LocalDate date) {

        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        Retry retry = Retry.of("id", config);

        CheckedFunction0<Demands> retryableSupplier = Retry.decorateCheckedSupplier(retry, () -> fetchDemands(date));

        Try<Demands> result = Try.of(retryableSupplier).recover((throwable) -> Demands.emptyDemands);

        return result.getOrElse(Demands.emptyDemands).getDemands();
    }

    private Demands fetchDemands(LocalDate date) {
        return restTemplate.getForObject("url", Demands.class);
    }

    @Getter
    @AllArgsConstructor
    private static class Demands {
        static Demands emptyDemands = new Demands(emptyList());

        private List<CurrentDemand> demands;
    }

}
