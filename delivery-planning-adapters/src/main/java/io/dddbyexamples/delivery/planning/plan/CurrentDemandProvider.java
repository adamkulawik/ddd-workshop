package io.dddbyexamples.delivery.planning.plan;

import java.time.LocalDate;
import java.util.List;

public interface CurrentDemandProvider {
    List<CurrentDemand> getByDate(LocalDate date);
}
