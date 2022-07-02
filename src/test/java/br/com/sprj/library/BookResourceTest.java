package br.com.sprj.library;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class BookResourceTest {

	@Test
	@Order(1)
	public void shouldListBooks() {
		given()
			.when()
				.get("/books")
				.then()
					.statusCode(200).body("$.size()", is(2), 
							"bookIsbn", containsInAnyOrder("0000000000001", "0000000000002"), 
							"bookTitle", containsInAnyOrder("The Book One", "The Book Two"), 
							"bookSubTitle", containsInAnyOrder("The First Book", "The Second Book"));
	}
	
	@Test
	@Order(2)
	public void shouldCreateABook() {
		given()
			.body("{\"bookIsbn\": \"0000000000003\","
				 + "\"bookTitle\": \"The Book Three\","
				 + "\"bookSubTitle\": \"The Third Book\"}")
			.header("Content-Type", MediaType.APPLICATION_JSON)
			.when()
				.post("/books")
				.then()
					.statusCode(201);
	}

	@Test
	@Order(3)
	public void shouldListBooksAfterCreation() {
		given()
			.when()
				.get("/books")
				.then()
					.statusCode(200)
						.body("$.size()", is(3), 
								"bookIsbn",	containsInAnyOrder("0000000000001", "0000000000002", "0000000000003"), 
								"bookTitle", containsInAnyOrder("The Book One", "The Book Two", "The Book Three"), 
								"bookSubTitle",	containsInAnyOrder("The First Book", "The Second Book", "The Third Book"));
	}

	@Test
	@Order(4)
	public void shouldDeleteABook() {
		given()
			.when()
				.delete("/books/3")
				.then()
					.statusCode(200);
	}

	@Test
	@Order(5)
	public void shouldListBooksAfterDeletion() {
		given()
			.when()
				.get("/books")
				.then()
					.statusCode(200).body("$.size()", is(2), 
							"bookIsbn", containsInAnyOrder("0000000000001", "0000000000002"), 
							"bookTitle", containsInAnyOrder("The Book One", "The Book Two"), 
							"bookSubTitle", containsInAnyOrder("The First Book", "The Second Book"));
	}

	@Test
	@Order(6)
	public void shouldUpdateABookUPD() {
		given()
		.body("{\"bookIsbn\": \"0000000000002\","
			 + "\"bookTitle\": \"The Book Two (2)\","
			 + "\"bookSubTitle\": \"The Second Book (2)\"}")
			.header("Content-Type", MediaType.APPLICATION_JSON)
			.when()
				.put("/books/2")
				.then()
					.statusCode(200);
	}
	
	@Test
	@Order(7)
	public void shouldListBooksAfterUpdateUPD() {
		given()
			.when()
				.get("/books")
				.then()
					.statusCode(200).body("$.size()", is(2), 
							"bookIsbn", containsInAnyOrder("0000000000001", "0000000000002"), 
							"bookTitle", containsInAnyOrder("The Book One", "The Book Two (2)"), 
							"bookSubTitle", containsInAnyOrder("The First Book", "The Second Book (2)"));
	}	
	
	@Test
	@Order(8)
	public void shouldUpdateABookINS() {
		given()
		.body("{\"bookIsbn\": \"0000000000003\","
			 + "\"bookTitle\": \"The Book Three\","
			 + "\"bookSubTitle\": \"The Third Book\"}")
			.header("Content-Type", MediaType.APPLICATION_JSON)
			.when()
				.put("/books/10")
				.then()
					.statusCode(200);
	}
	
	@Test
	@Order(9)
	public void shouldListBooksAfterUpdateINS() {
		given()
		.when()
			.get("/books")
			.then()
				.statusCode(200).body("$.size()", is(3), 
						"bookIsbn", containsInAnyOrder("0000000000001", "0000000000002", "0000000000003"), 
						"bookTitle", containsInAnyOrder("The Book One", "The Book Two (2)", "The Book Three" ), 
						"bookSubTitle", containsInAnyOrder("The First Book", "The Second Book (2)", "The Third Book"));
	
	}	
	
}