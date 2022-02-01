package com.sinensia.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestClientException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class DemoProjectApplicationTests {

	@Autowired TestRestTemplate restTemplate;

	@Test /* "Error en carga de contexto " */
	void contextLoads() {
	}

	//@Test
	//void manualError() throws Exception {
	//	throw new Exception("Error generado manualmente en carga de contexto ");
	//}

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

	@Test
	void helloTest (@Autowired TestRestTemplate restTemplate) {
		assertThat(restTemplate.getForObject("/hello", String.class)).isEqualTo("Hello World!");
		assertThat(restTemplate.getForObject("/hello?name=Robert", String.class)).isEqualTo("Hello Robert!");
	}

	@Test
	void canAddTest (@Autowired TestRestTemplate restTemplate) {
		//assertThat(restTemplate.getForObject("/add?a=1&b=2", String.class)).isEqualTo("This add (1 + 2) is 3");
		assertThat(restTemplate.getForObject("/add?a=1&b=2", String.class)).isEqualTo("3");
	}

	@Test
	void canAddNullA (@Autowired TestRestTemplate restTemplate) {
		assertThat(restTemplate.getForObject("/add?a=&b=2", String.class)).isEqualTo("2");
	}

	@Test
	void canAddNullB (@Autowired TestRestTemplate restTemplate) {
		assertThat(restTemplate.getForObject("/add?a=1&b=", String.class)).isEqualTo("1");
	}

	@Test
	void canAddFraction (@Autowired TestRestTemplate restTemplate) {
		assertThat(restTemplate.getForObject("/add?a=3.45&b=3.55", String.class)).isEqualTo("7");
	}

	@ParameterizedTest
	@CsvSource({
			"a, Hello a!",
			"b, Hello b!",
			//"first%20last, Hello first last!"
	})
	void helloParamNamesCsv(String name, String expected) {
		assertThat(restTemplate.getForObject("/hello?name="+name, String.class)).isEqualTo(expected);
	}

	@Nested
	@DisplayName(value="Application tests")
	class AppTests {

		@Autowired
		private DemoProjectApplication app;

		@Test
		void appCanAddReturnsInteger() {
			assertThat(app.canAdd(1f, 2f)).isEqualTo(3);
		}

		@Test
		void appCanAddReturnsFloat() {
			assertThat(app.canAdd(1.5f, 2.0f)).isEqualTo(3.5f);
		}

		@Test
		void appCanAddNull() {
			Exception thrown = assertThrows(NullPointerException.class,() -> {
				Float ret = (Float) app.canAdd( null, 2f);
			});
			assertTrue(thrown.toString().contains("NullPointerException"));
			//thrown.getMessage().contains("");              alternativamente comprovar que contiene ""
		}
	}

	@Nested
	class MultiplicationTest {

		@DisplayName("multiplication")
		@ParameterizedTest(name="{displayName} [{index}] {0} * {1} = {2}")
		@CsvSource({
				"1,   2,   2",
				"1,   1,   1",
				"1.0, 1.0, 1",
				"1,  -2,  -2",
				"1.5, 2,   3",
				"'',  2,   0",
				"1.5, 1.5, 2.25"
		})
		void canMultiply(String a, String b, String expected) {
			assertThat(restTemplate.getForObject("/mult?a="+a+"&b="+b, String.class))
					.isEqualTo(expected);
		}

	}

	@Nested
	class DivideTests {
		@DisplayName("multiple divisions")
		@ParameterizedTest(name = "{displayName} [{index}] {0} / {1} = {2}")
		@CsvSource({
				"10,   2,   5.00",
				"10,  -1, -10.00",
				" 1.0, 1.0, 1.00",
				"10,   3,   3.33"
		})
		void canAddCsvParameterizedFloat(String a, String b, String expected) {
			assertThat(restTemplate.getForObject("/div?a=" + a + "&b=" + b, Float.class))
					.isEqualTo(Float.parseFloat(expected));
		}

		@Test
		void divideByZero() {
			Exception thrown = assertThrows(RestClientException.class, () -> {
				restTemplate.getForObject("/div?a=10&b=0", Float.class);
			});
		}
	}





}

