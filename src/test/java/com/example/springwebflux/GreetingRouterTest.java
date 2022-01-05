package com.example.springwebflux;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.springwebflux.entity.Greeting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void testHello() {
    webTestClient
        .get().uri("/hello")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Greeting.class).value(greeting -> assertThat(greeting.getMessage()).isEqualTo("Hello, Spring!"));
  }
}