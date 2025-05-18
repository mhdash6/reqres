package testUtils.TestFactories;

import org.testng.annotations.Factory;
import reqres.model.User;
import testClasses.*;
import testUtils.TestDataProviders.TestDataProvider;

public class TestFactory {

    @Factory(dataProvider = "CreateUser", dataProviderClass = TestDataProvider.class)
    public Object[] createUserTestFactory(User user, int expectedResponseCode) {
        return new Object[] {
                new CreateUserTest(user,expectedResponseCode)};
    }
    @Factory(dataProvider = "UpdateUser", dataProviderClass = TestDataProvider.class)
    public Object[] updateUserTestFactory(int id, User user, int expectedResponseCode) {
        return new Object[] {
                new UpdateUserTest(id,user,expectedResponseCode)};
    }

    @Factory(dataProvider = "DeleteUser", dataProviderClass = TestDataProvider.class)
    public Object[] deleteUserTestFactory(int id, int expectedResponseCode){
        return new Object[] {
                new DeleteUserTest(id,expectedResponseCode)};
    }
    @Factory(dataProvider = "GetUser", dataProviderClass = TestDataProvider.class)
    public Object[] getUserTestFactory(int id,int pageNumber ,int expectedResponseCode){
        return new Object[] {
                new GetUserTest(id,pageNumber,expectedResponseCode)};
    }

    @Factory
    public Object[] LoginTestFactory(){
        return new Object[]{
                new LoginTest()
        };
    }

}
