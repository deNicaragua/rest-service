package com.example.restservice;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestServiceApplicationTests {

	@Test
	void testHttpClientCall() {

	/*	stubFor(WireMock.get(urlMatching("/any"))
				.willReturn(aResponse()
						.withStatus(OK.value())));

		Mockito.when(repository.save(employee)).thenReturn(Mono.just(employee));

		webClient.post()
				.uri("/create")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(employee))
				.exchange()
				.expectStatus().isCreated();

		Mockito.verify(repository, times(1)).save(employee);*/
	}

}
