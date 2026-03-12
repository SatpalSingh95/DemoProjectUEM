package shopper;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class ShopperLogin {

	// @Test
	public void loginTest() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "satpal110095@gmail.com");
		map.put("password", "Singh@11990");
		map.put("role", "SHOPPER");

		given().relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json")
				.body(map).when()
				.post("https://www.shoppersstack.com/shopping/users/login").then().log().all();

	}

	String userId;
	String jwtToken;

	@Test
	public void loginTest2() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", "satpal110095@gmail.com");
		map.put("password", "Singh@11990");
		map.put("role", "SHOPPER");

		Response res = given().relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json").body(map).when()
				.post("https://www.shoppersstack.com/shopping/users/login");
                 res.then().log().all();
		userId = res.jsonPath().getString("data.userId");
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println(userId + "   " + jwtToken);

	}

	@Test(dependsOnMethods = "loginTest2")
	public void FetchDataLoginTest() {
		given().relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json").auth().oauth2(jwtToken).pathParam("shopperId", userId)

				.when().get("https://www.shoppersstack.com/shopping/shoppers/{shopperId}").then().log().all();

	}

	@Test(dependsOnMethods = "loginTest2")
	public void UpdateDataTest() {

		HashMap map = new HashMap();
		map.put("city", "Kolkata");
		map.put("country", "India");
		map.put("email", "satpal110095@gmail.com");
		map.put("firstName", "satpalsingh");
		map.put("gender", "MALE");
		map.put("lastName", "singh");
		map.put("password", "Satpal@11990");
		map.put("phone", "7007759745");
		map.put("state", "Bengal");
		map.put("zoneId", "ALPHA");

		given().relaxedHTTPSValidation() /* Ignore SSL certificate validation. */
				.contentType("application/json").auth().oauth2(jwtToken).pathParam("shopperId", userId).body(map).when()
				.patch("https://www.shoppersstack.com/shopping/shoppers/{shopperId}").then()
				
				.assertThat().statusCode(200)
				.log().all();

	}
}
