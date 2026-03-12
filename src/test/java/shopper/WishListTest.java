package shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WishListTest {
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

}
