package br.com.farias.rest_com_springboot_e_java.integrationtests.swagger;

import static io.restassured.RestAssured.given;

import br.com.farias.rest_com_springboot_e_java.config.TestConfigs;
import br.com.farias.rest_com_springboot_e_java.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	@DisplayName("JUnit test Should Display Swagger Ui Page")
	void testShouldDisplaySwaggerUiPage() {

		var content = given()
				.basePath("/swagger-ui/index.html")
				.port(TestConfigs.SERVER_PORT)
				.when()
					.get()
				.then()
					.statusCode(200)
				.extract()
					.body()
						.asString();
		Assertions.assertTrue(content.contains("Swagger UI"));

	}

}
