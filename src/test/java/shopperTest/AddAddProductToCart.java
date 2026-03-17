package shopperTest;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import shopperpro_pojo.AddProductToCartPojo;
import shopperpro_pojo.UpdateCartPojo;

import static io.restassured.RestAssured.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AddAddProductToCart extends BaseClass {
	 int productId;
	 int quantity;
	 int itemId;
	 
	 @Test
		public void fetchAllProducts() throws Exception {
			Response res = given()
			.auth().oauth2(jwtToken)
			.relaxedHTTPSValidation()
			.contentType("application/json")
			.baseUri("https://www.shoppersstack.com/shopping")
			.when()
					.get("/products/alpha");
		
			 List<Integer> productIds = res.jsonPath().get("data.productId");
			 productId = productIds.get(0);
			 System.out.println("product id is : "+productId);
			 quantity = res.jsonPath().getInt("data[0].quantity");
			 System.out.println("quantity is : "+quantity);
				 
			res.then().
			assertThat().statusCode(200);
		
			 
			// res.then().log().all();
			// System.out.println(res.prettyPrint());
			 
			

			 FileWriter file = new FileWriter("response.json");
			 file.write(res.asPrettyString());
			 file.close();

/*
			 List<Integer> quantities = res.jsonPath().getList("data.quantity");
			 quantity = quantities.get(0);
			 System.out.println("quantity is : "+quantity);
			 */
			 	 
	 }
	@Test(dependsOnMethods = "fetchAllProducts")
	public void addProductToCart() {

	    AddProductToCartPojo cart =
	            new AddProductToCartPojo(productId, quantity);

	  Response res = given()
	        .auth().oauth2(jwtToken)
	        .relaxedHTTPSValidation()
	        .contentType("application/json")
	        .baseUri("https://www.shoppersstack.com/shopping")
	        .pathParam("shopperId", shopperId)
	        .body(cart)
	    .when()
	        .post("/shoppers/{shopperId}/carts");	
	    itemId = res.jsonPath().get("data.itemId");
	  res.then()
	  .assertThat().statusCode(201)
	  .log().all();
	}
	@Test(dependsOnMethods = "addProductToCart")
	public void updateCart() {

	   UpdateCartPojo update =
	            new  UpdateCartPojo(productId, quantity);

	  Response res = given()
	        .auth().oauth2(jwtToken)
	        .relaxedHTTPSValidation()
	        .contentType("application/json")
	        .baseUri("https://www.shoppersstack.com/shopping")
	        .pathParam("shopperId", shopperId)
	        .pathParam("itemId", itemId)
	        .body(update)
	    .when()
	        .put("/shoppers/{shopperId}/carts/{itemId}");	
	  res.then()
	  .assertThat().statusCode(200)
	  .log().all();
	}
	@Test(dependsOnMethods = "updateCart")
	public void deleteCart() {


	  Response res = given()
	        .auth().oauth2(jwtToken)
	        .relaxedHTTPSValidation()
	        .contentType("application/json")
	        .baseUri("https://www.shoppersstack.com/shopping")
	        .pathParam("shopperId", shopperId)
	        .pathParam("productId", productId)
	    .when()
	        .delete("/shoppers/{shopperId}/carts/{productId}");	
	  res.then()
	  .assertThat().statusCode(200)
	  .log().all();
	  System.out.println("some changes in Cart");
	}
	
}
