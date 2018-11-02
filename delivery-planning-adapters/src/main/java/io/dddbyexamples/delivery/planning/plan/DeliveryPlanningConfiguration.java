package io.dddbyexamples.delivery.planning.plan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DeliveryPlanningConfiguration {

    @Bean PlanCompletenessProvider planCompletenessProvider() {
        return new SimplePlanCompletenessProvider();
    }
}
