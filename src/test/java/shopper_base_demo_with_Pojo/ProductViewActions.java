package shopper_base_demo_with_Pojo;

import static io.restassured.RestAssured.given;

import java.util.List;

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
	
		// productId = res.jsonPath().getInt("data[0].productId");
		// productId = res.jsonPath().getString("data[0].productId");
		 res.then().log().all();
		 List<Integer> productIds = res.jsonPath().getList("data.productId");

		 int productId = productIds.get(1);
		 System.out.println("product id is : "+productId);
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 /*
		 List<Integer> quantitys = res.jsonPath().getList("data.quantity");
		 int quantity=quantitys.get(0);
		 System.out.println(quantity);
		 res.prettyPrint();*/
		
	}
}
