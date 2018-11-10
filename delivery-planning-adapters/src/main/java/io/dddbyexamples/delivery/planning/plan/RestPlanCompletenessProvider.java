package io.dddbyexamples.delivery.planning.plan;

import io.dddbyexamples.delivery.planning.Amounts;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

class RestPlanCompletenessProvider implements PlanCompletenessProvider {

    private final CurrentDemandProvider currentDemandProvider;

    RestPlanCompletenessProvider(CurrentDemandProvider currentDemandProvider) {
        this.currentDemandProvider = currentDemandProvider;
    }

    @Override
    public PlanCompleteness get(LocalDate date) {
        Amounts demanded = provideDemanded(date);
        Amounts planned = providePlanned(date);
        return new PlanCompleteness(date, planned, demanded, Amounts.empty());
    }

    private Amounts providePlanned(LocalDate date) {
        return Amounts.empty();
    }

    private Amounts provideDemanded(LocalDate date) {
        List<CurrentDemand> demands = currentDemandProvider.getByDate(date);
        Map<String, Long> map = demands.stream().collect(toMap(CurrentDemand::getRefNo, CurrentDemand::getLevel));
        return new Amounts(map);
    }
}

@Value
class CurrentDemand {
    private String refNo;
    private LocalDate date;
    private long level;
}