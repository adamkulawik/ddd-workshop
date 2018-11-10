package io.dddbyexamples.delivery.planning.plan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

public class TestCurrentDemandProvider implements CurrentDemandProvider {
    private final List<CurrentDemand> demands;
    private final List<CurrentDemand> planned;

    public TestCurrentDemandProvider() {
        demands = Collections.emptyList();
        planned = Collections.emptyList();
    }

    public TestCurrentDemandProvider(List<CurrentDemand> currentPlan, List<CurrentDemand> currentDemands) {
        demands = new ArrayList<>();
        demands.addAll(currentDemands);

        planned = new ArrayList<>();
        planned.addAll(currentPlan);
    }

    @Override
    public List<CurrentDemand> getByDate(LocalDate date) {
        return demands;
    }
}
