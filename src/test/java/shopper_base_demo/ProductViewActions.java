package shopper_base_demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ProductViewActions extends BaseClass {

	
	@Test
	public void fetchAllProducts() {
		Response res = given()
		.auth().oauth2(jwtToken)
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.baseUri("https://www.shoppersstack.com/shopping")
		.when()
				.get("/products/alpha");
	
		 productId = res.jsonPath().getInt("data[0].productId");
		 res.prettyPrint();
		
	}
}
