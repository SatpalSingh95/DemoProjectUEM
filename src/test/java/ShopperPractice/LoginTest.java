package ShopperPractice;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LoginTest {
	String shopperId;
	String jwtToken;

	@Test
	public void shopperLogin() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "satpal110095@gmail.com");
		map.put("password", "Singh@11990");
		map.put("role", "SHOPPER");

		Response res = given().relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json").body(map).when()
				.post("https://www.shoppersstack.com/shopping/users/login");

		shopperId = res.jsonPath().getString("data.userId");
		System.out.println("shopperId is nothing but userId  " + shopperId);

		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println("Bearer token  " + jwtToken);

		/*
		 * // res.then().log().all(); userId = res.jsonPath().getString("data.userId");
		 * jwtToken = res.jsonPath().getString("data.jwtToken");
		 * System.out.println(userId + "   " + jwtToken);
		 */

	}

	@Test(dependsOnMethods = "shopperLogin")
	public void fetchData() {

		Response res = given()
				.relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json")
				.auth().oauth2(jwtToken)
				.pathParam("shopperId", shopperId)
				.baseUri("https://www.shoppersstack.com/shopping")
				.when()
				.get("/shoppers/{shopperId}");
		System.out.println(res.prettyPrint());
	}
}
