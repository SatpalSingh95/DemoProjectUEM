package shopper_base_demo_with_Pojo;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.response.Response;

public class ShopperCart extends BaseClass {
	@Test
	public void addProductToCart() {
		
		JSONObject jObj=new JSONObject();
		//jObj.put("productId", productId);
		jObj.put("productId", productId);

		jObj.put("quantity",quantity);
		given()
		.auth().oauth2(jwtToken)
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.baseUri("https://www.shoppersstack.com/shopping")
		.pathParam("shopperId", shopperId)
		.body(jObj)
		.when()
				.post("/shoppers/{shopperId}/carts")
				.then().log().all();
	}
	@Test
	public void fetchAllProducts() {
		given()
		.auth().oauth2(jwtToken)
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.baseUri("https://www.shoppersstack.com/shopping")
		.pathParam("shopperId", shopperId)
		.when()
				.get("/shoppers/{shopperId}/carts")
				.then().log().all();

		// Using values from BaseClass
		System.out.println(shopperId + "   " + jwtToken);
	}
}