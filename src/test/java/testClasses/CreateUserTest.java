package testClasses;

import io.restassured.response.Response;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import reqres.UserUtils;
import reqres.model.CreateUserResponse;
import reqres.model.User;



public class CreateUserTest extends BaseTest {

    User newUser;
    int expectedResponseCode;

    public CreateUserTest(User addedUser,int expectedResponseCode) {
        this.newUser=addedUser;
        this.expectedResponseCode=expectedResponseCode;
    }



    @Test
     public void createUser(){
        SoftAssert softAssert = new SoftAssert();
        Response response= UserUtils.createUser(token,newUser,expectedResponseCode);
        CreateUserResponse createUserResponse=  response.as(CreateUserResponse.class);
        softAssert.assertEquals(createUserResponse.name,newUser.name);
    }


}
