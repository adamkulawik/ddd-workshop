package io.dddbyexamples.delivery.planning.plan

import spock.lang.Specification

import java.time.LocalDate

class PlanCompletenessProviderSpec extends Specification {

    PlanCompletenessProvider planCompletenessProvider

    def setup() {
        planCompletenessProvider = new DeliveryPlanningConfiguration().planCompletenessProvider()
    }

    def 'should provide empty plan completness'() {
        def date = LocalDate.now()
        given:
        when:
        PlanCompleteness planCompletness = planCompletenessProvider.get(date)
        then:
        !planCompletness.anyMissing()
    }
}
