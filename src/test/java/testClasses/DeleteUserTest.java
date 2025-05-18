package testClasses;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import reqres.UserUtils;

public class DeleteUserTest extends BaseTest {

    int id;
    int expectedResponseCode;
    public DeleteUserTest(int id,int expectedResponseCode) {
        this.id=id;
        this.expectedResponseCode=expectedResponseCode;
    }

    @Test
    public void deleteUser(){
      UserUtils.deleteUser(id,token,expectedResponseCode);
    }
}
