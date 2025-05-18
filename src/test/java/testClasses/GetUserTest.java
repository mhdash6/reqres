package testClasses;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import reqres.UserUtils;
import reqres.model.PageUsersModel;
import reqres.model.SingleUserModel;

public class GetUserTest extends BaseTest {

    int id;
    int pageNumber;
    int expectedResponseCode;
    public GetUserTest(int id,int pageNumber,int expectedResponseCode)
    {
        this.id=id;
        this.pageNumber =pageNumber;
        this.expectedResponseCode=expectedResponseCode;
    }


    @Test
    public void getUser(){
         SoftAssert s = new SoftAssert();
         SingleUserModel user = UserUtils.getUser(id,expectedResponseCode,token).as(SingleUserModel.class);
         s.assertEquals((Integer) id, user.data.id);
         s.assertAll();
    }
    @Test
    public void getUsersInPage(){
        SoftAssert s = new SoftAssert();
        PageUsersModel pageUsersModel=  UserUtils.getUsersByPage(pageNumber,expectedResponseCode,token).as(PageUsersModel.class);
        s.assertEquals((Integer) pageNumber, pageUsersModel.page);
        s.assertAll();
    }

}
