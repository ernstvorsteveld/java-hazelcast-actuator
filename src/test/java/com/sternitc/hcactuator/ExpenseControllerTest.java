package com.sternitc.hcactuator;

import com.sternitc.hcactuator.expenses.ExpenseController;
import com.sternitc.hcactuator.expenses.ExpensePayload;
import com.sternitc.hcactuator.expenses.HazelcastActuatorConfiguration;
import com.sternitc.hcactuator.expenses.InvokerCounter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ExpenseController.class)
@AutoConfigureMockMvc
@Import({HazelcastActuatorConfiguration.class})
class ExpenseControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private InvokerCounter counter;

    @Test
    public void should_save_expense() throws Exception {
        var uuid = UUID.randomUUID().toString();
        var description = "Test";
        var amount = 10.0;
        this.webClient.post().uri("/expenses/").
                body(BodyInserters.fromObject(
                        new ExpensePayload(uuid, description, amount)))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(uuid.toString())
                .jsonPath("$.description").isEqualTo(description)
                .jsonPath("$.amount").isEqualTo(amount);
    }

    @Test
    public void should_use_cache() {
        var uuid = UUID.randomUUID().toString();
        var description = "Test";
        var amount = 10.0;
        this.webClient.post().uri("/expenses/").
                body(BodyInserters.fromObject(
                        new ExpensePayload(uuid, description, amount)))
                .exchange()
                .expectStatus().isOk();
        assertThat(counter.getCount()).isEqualTo(1);

        this.webClient.get().uri("/expenses/" + uuid).exchange().expectStatus().isOk();
        assertThat(counter.getCount()).isEqualTo(2);

        this.webClient.get().uri("/expenses/" + uuid).exchange().expectStatus().isOk();
        assertThat(counter.getCount()).isEqualTo(2);

        this.webClient.get().uri("/expenses/" + uuid).exchange().expectStatus().isOk();
        assertThat(counter.getCount()).isEqualTo(2);
    }
}