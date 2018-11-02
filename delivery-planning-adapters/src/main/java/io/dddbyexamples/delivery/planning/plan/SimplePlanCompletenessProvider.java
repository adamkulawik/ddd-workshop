package io.dddbyexamples.delivery.planning.plan;

import java.time.LocalDate;

class SimplePlanCompletenessProvider implements PlanCompletenessProvider {
    @Override
    public PlanCompleteness get(LocalDate date) {
        return null;
    }
}
