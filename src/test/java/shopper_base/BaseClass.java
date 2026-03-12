package shopper_base;

import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class BaseClass {
	String shopperId;
	String jwtToken;
	@BeforeClass
	 public void login() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", "satpal110095@gmail.com");
        map.put("password", "Singh@11990");
        map.put("role", "SHOPPER");

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(map)
        .when()
                .post("https://www.shoppersstack.com/shopping/users/login");

        res.then().log().all();

        shopperId = res.jsonPath().getString("data.userId");
        jwtToken = res.jsonPath().getString("data.jwtToken");

        System.out.println("ShopperId : " + shopperId);
        System.out.println("Token : " + jwtToken);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
    public static String jwtToken;
    public static String shopperId;

    @BeforeClass
    public void login() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("email", "satpal110095@gmail.com");
        map.put("password", "Singh@11990");
        map.put("role", "SHOPPER");

        Response res = given()
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(map)
        .when()
                .post("https://www.shoppersstack.com/shopping/users/login");

        res.then().log().all();

        shopperId = res.jsonPath().getString("data.userId");
        jwtToken = res.jsonPath().getString("data.jwtToken");

        System.out.println("ShopperId : " + shopperId);
        System.out.println("Token : " + jwtToken);
        
        
        */
    }
}