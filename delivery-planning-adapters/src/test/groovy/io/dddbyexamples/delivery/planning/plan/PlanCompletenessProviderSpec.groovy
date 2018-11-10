package io.dddbyexamples.delivery.planning.plan

import spock.lang.Specification

import java.time.LocalDate

import static java.util.Collections.singletonList

class PlanCompletenessProviderSpec extends Specification {

    PlanCompletenessProvider planCompletenessProvider
    CurrentDemandProvider currentDemandProvider

    def 'should provide empty plan completness'() {
        given:
        def date = LocalDate.now()
        currentDemandProvider = new TestCurrentDemandProvider()
        planCompletenessProvider = new RestPlanCompletenessProvider(currentDemandProvider)

        when:
        PlanCompleteness planCompletness = planCompletenessProvider.get(date)
        then:
        !planCompletness.anyMissing()
    }

    def 'should provide fullfiled plan complentness'() {
        given:
        def date = LocalDate.now()

        currentDemandProvider = new TestCurrentDemandProvider(plan(date), demand(date))
        planCompletenessProvider = new RestPlanCompletenessProvider(currentDemandProvider)

        when:
        PlanCompleteness planCompletness = planCompletenessProvider.get(date)
        then:
        !planCompletness.anyMissing()
    }

    List<CurrentDemand> plan(LocalDate date) {
        singletonList(new CurrentDemand("refNo", date, 100))
    }

    List<CurrentDemand> demand(LocalDate date) {
        singletonList(new CurrentDemand("refNo", date, 100))
    }
}
