package testClasses;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.Constants;
import reqres.LoginUtils;

public class LoginTest {

    public LoginTest() {
    }


    @Test
    public static void validLogin(){
       Response response= LoginUtils.login(Constants.VALID_EMAIL,Constants.VALID_PASSWORD,200);
       String token= LoginUtils.getToken(response);
       Assert.assertNotNull(token);
    }

    @Test
    public  static  void invalidLogin(){
        Response response= LoginUtils.login(Constants.VALID_EMAIL,"",400);
        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals(jsonPath.getString("error"), "Missing password");
    }
}
