package testClasses;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reqres.UserUtils;
import reqres.model.UpdatedUserModel;
import reqres.model.User;

public class UpdateUserTest extends BaseTest {

    int id;
    User user;
    int expectedResponseCode;
    public UpdateUserTest(int id,User updatedUser,int expectedResponseCode) {
        this.id=id;
        this.expectedResponseCode=expectedResponseCode;
        user=updatedUser;
    }

    @Test
    public  void updateUser(){
        SoftAssert softAssert = new SoftAssert();
        UpdatedUserModel updatedUser = UserUtils.updateUser(id,token,user,expectedResponseCode)
                .as(UpdatedUserModel.class);
        softAssert.assertEquals(updatedUser.name,user.name);
        softAssert.assertAll();
    }
}
