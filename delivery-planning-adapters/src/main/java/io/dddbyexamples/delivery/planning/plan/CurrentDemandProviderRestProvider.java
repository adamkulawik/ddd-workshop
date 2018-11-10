package io.dddbyexamples.delivery.planning.plan;

import java.time.LocalDate;
import java.util.List;

class CurrentDemandProviderRestProvider implements CurrentDemandProvider {
    // TODO: 10.11.18 implementation + Resillience4j
    @Override
    public List<CurrentDemand> getByDate(LocalDate date) {
        return null;
    }
}
