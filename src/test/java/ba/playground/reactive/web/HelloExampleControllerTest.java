package ba.playground.reactive.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloExampleControllerTest {


    @Autowired
    private WebTestClient webClient;

    @Test
    public void test_simple() {
        //Given


        // When
        ArrayList responseBody = webClient.get().uri("/hello")
                .exchange()
                .expectBody(ArrayList.class).returnResult().getResponseBody();

        // Then
        assertThat(responseBody).isNotNull();
        assertThat((ArrayList<String>) responseBody.get(0))
                .isNotNull()
                .isNotEmpty()
                .hasSize(6)
                .contains("test1", "test2", "test3", "test4", "test5", "test6");

    }
}
