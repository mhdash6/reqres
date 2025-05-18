package testClasses;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import reqres.Constants;
import reqres.LoginUtils;

abstract public class BaseTest {
    static String token;
    @BeforeClass
   public void login(){
        Response response= LoginUtils.login(Constants.VALID_EMAIL,Constants.VALID_PASSWORD,200);
        token= LoginUtils.getToken(response);
    }
}
