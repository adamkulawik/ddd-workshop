package io.dddbyexamples.delivery.planning.plan

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

import static com.github.tomakehurst.wiremock.client.WireMock.*

@SpringBootTest(classes = AppRunner.class)
public class PlanCompletenessProviderIntegrationSpec extends Specification {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089)

    @Autowired
    PlanCompletenessProvider planCompletenessProvider

    @Value('${rest.url.demands}')
    String demandsUrl;

    def 'shouldReturnPlancCompleteness'() {
        given: "there is working endpoint"
        wireMockRule.
                stubFor(get(urlEqualTo(demandsUrl))
                        .willReturn(okJson("")
                        .withStatus(200)))

        when: planCompletenessProvider.get(LocalDate.now())
        then: true
    }
}
