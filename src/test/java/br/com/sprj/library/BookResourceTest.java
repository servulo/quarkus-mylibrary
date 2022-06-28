package br.com.sprj.library;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class BookResourceTest {
	
	@Test
	public void testList() {
		given()
			.when().get("/books")
			.then()
				.statusCode(200)
				.body("$.size()", is(2),
						"title", containsInAnyOrder("Book One","Book Two"),
						"subTitle", containsInAnyOrder("The First Book", "The Second Book"));
	}

	@Test
	public void testAdd() {
		given()
			.body("{\"title\": \"Book Three\", \"subTitle\": \"The Third Book\"}")
			.header("Content-Type", MediaType.APPLICATION_JSON)
			.when().post("/books")
			.then()
				.statusCode(200)
				.body("$.size()", is(3),
						"title", containsInAnyOrder("Book One","Book Two", "Book Three"),
						"subTitle", containsInAnyOrder("The First Book", "The Second Book", "The Third Book"));
	}
	
	@Test
	public void testDelete() {
		given()
			.body(("{\"title\": \"Book Three\", \"subTitle\": \"The Third Book\"}"))
			.header("Content-Type", MediaType.APPLICATION_JSON)
			.when().delete("/books")
			.then()
				.statusCode(200)
				.body("$.size()", is(2),
						"title", containsInAnyOrder("Book One","Book Two"),
						"subTitle", containsInAnyOrder("The First Book", "The Second Book"));
	}

}
