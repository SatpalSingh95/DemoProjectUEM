package shopper_base_demo_with_Pojo;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
public class ShopperLogin extends BaseClass {
	@Test
	public void loginTest() {
		ShopperLoginPojo pojo=new ShopperLoginPojo("satpal110095@gmail.com","Singh@11990","SHOPPER");
	 
	    Response res = given()
	            .relaxedHTTPSValidation()
	            .contentType("application/json")
	            .body(pojo)
	    .when()
	            .post("https://www.shoppersstack.com/shopping/users/login");

	    res.then().log().all();

	    // Using values from BaseClass
	    System.out.println(shopperId + " " + jwtToken);
	}
	
	
	
	

    @Test
    public void FetchDataLoginTest() {

        given()
        .relaxedHTTPSValidation()
        .auth().oauth2(jwtToken)
        .pathParam("shopperId", shopperId)
        .when()
        .get("https://www.shoppersstack.com/shopping/shoppers/{shopperId}")
        .then()
        .log().all();
    }

    @Test
    public void UpdateDataTest() {

        ShopperUpdatePojo data = new ShopperUpdatePojo();

        data.setCity("Kolkata");
        data.setCountry("India");
        data.setEmail("satpal110095@gmail.com");
        data.setFirstName("satpalsingh");
        data.setGender("MALE");
        data.setLastName("singh");
        data.setPassword("Satpal@11990");
        data.setPhone("7007759745");
        data.setState("Bengal");
        data.setZoneId("ALPHA");

        given()
        .relaxedHTTPSValidation()
        .contentType("application/json")
        .auth().oauth2(jwtToken)
        .pathParam("shopperId", shopperId)
        .body(data)
        .when()
        .patch("https://www.shoppersstack.com/shopping/shoppers/{shopperId}")
        .then()
        .log().all();
    }
}