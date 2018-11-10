package io.dddbyexamples.delivery.planning.plan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PlanCompletenessConfiguration {

    @Bean
    PlanCompletenessProvider planCompletenessProvider(CurrentDemandProvider currentDemandProvider) {
        return new RestPlanCompletenessProvider(currentDemandProvider);
    }
}
