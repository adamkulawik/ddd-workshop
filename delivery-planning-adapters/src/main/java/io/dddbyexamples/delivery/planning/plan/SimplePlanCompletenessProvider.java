package io.dddbyexamples.delivery.planning.plan;

import io.dddbyexamples.delivery.planning.Amounts;
import lombok.Value;

import java.time.LocalDate;

class SimplePlanCompletenessProvider implements PlanCompletenessProvider {

    @Override
    public PlanCompleteness get(LocalDate date) {
        return new PlanCompleteness(date, Amounts.empty(), Amounts.empty(), Amounts.empty());
    }
}
