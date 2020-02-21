package ba.playground.reactive.web;

import ba.playground.reactive.model.Message;
import ba.playground.reactive.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private WebTestClient webClient;

    @Before
    public void setup() {
        Flux<Message> deleteAndInsert = messageRepository.deleteAll()
                .thenMany(messageRepository.saveAll(Flux.just(
                        new Message().setId(1L).setMessage("test1"),
                        new Message().setId(2L).setMessage("test2"),
                        new Message().setId(3L).setMessage("test3")
                        ))
                );

        StepVerifier
                .create(deleteAndInsert)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void find_all_messages() {
        List responseBody = webClient.get()
                .uri("/messages/all")
                .exchange()
                .expectBody(List.class)
                .returnResult()
                .getResponseBody();

        System.out.println(responseBody);

        assertThat((List<Message>) responseBody)
                .isNotNull()
                .hasSize(3);
    }
}
