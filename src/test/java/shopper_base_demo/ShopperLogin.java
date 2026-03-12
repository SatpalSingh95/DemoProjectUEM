package shopper_base_demo;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;
public class ShopperLogin extends BaseClass {

    @Test
    public void loginTest() {

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

        // Using values from BaseClass
        System.out.println(shopperId + "   " + jwtToken);
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

        HashMap<String,Object> map = new HashMap<>();

        map.put("city","Kolkata");
        map.put("country","India");
        map.put("email","satpal110095@gmail.com");
        map.put("firstName","satpalsingh");
        map.put("gender","MALE");
        map.put("lastName","singh");
        map.put("password","Satpal@11990");
        map.put("phone","7007759745");
        map.put("state","Bengal");
        map.put("zoneId","ALPHA");

        given()
        .relaxedHTTPSValidation()
        .contentType("application/json")
        .auth().oauth2(jwtToken)
        .pathParam("shopperId", shopperId)
        .body(map)
        .when()
        .patch("https://www.shoppersstack.com/shopping/shoppers/{shopperId}")
        .then()
        .log().all();
    }
}