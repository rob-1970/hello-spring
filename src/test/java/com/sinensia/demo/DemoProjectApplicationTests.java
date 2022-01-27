package com.sinensia.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class DemoProjectApplicationTests {

	@Test /* "Error en carga de contexto " */
	void contextLoads() {
	}

	@Test
	void manualError() throws Exception {
		throw new Exception("Error generado manualmente en carga de contexto ");
	}

//	@Test /* REALIZAR una prueba en navegador sobre la url RAIZ para cualquier puerto */
//	void rootTest (@Autowired WebTestClient webClient) { /* Autowired INDEXACIO o ANOTACIO MAGICA de SPRING */
//		webClient
//				.get().uri("/")
//				.exchange()
//				.expectStatus().isOk()                                          /* Verificar que retorna que es CORRECTE*/
//				.expectBody(String.class).isEqualTo("This is the Main branch!");/* Verificar que retorna "Hello World" */
//	}

	@Test /* REALIZAR una prueba en navegador sobre la url RAIZ para cualquier puerto */
	void startTest (@Autowired TestRestTemplate restTemplate) { /* Autowired INDEXACIO o ANOTACIO MAGICA de SPRING */
		assertThat(restTemplate.getForObject("/", String.class)).isEqualTo("This is the Main branch!");
		assertThat(restTemplate.getForObject("/?branch=La PERA", String.class)).isEqualTo("This is the La PERA branch!");
	}

	@Test /* REALIZAR una prueba en navegador sobre la url RAIZ para cualquier puerto */
	void helloTest (@Autowired TestRestTemplate restTemplate) { /* Autowired INDEXACIO o ANOTACIO MAGICA de SPRING */
		assertThat(restTemplate.getForObject("/hello", String.class)).isEqualTo("Hello World!");
		assertThat(restTemplate.getForObject("/hello?name=Robert", String.class)).isEqualTo("Hello Robert!");
	}

}
