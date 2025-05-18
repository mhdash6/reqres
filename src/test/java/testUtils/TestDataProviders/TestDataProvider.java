package testUtils.TestDataProviders;

import org.testng.annotations.DataProvider;
import reqres.model.User;
import testUtils.TestDataModel.ExpectedStatusCodes;
import testUtils.TestDataModel.TestData;
import static utilities.JsonUtils.readJson;


public class TestDataProvider {

    ExpectedStatusCodes expectedStatusCodes = readJson("src/test/resources/ExpectedCodes.json", ExpectedStatusCodes.class);
    TestData testData = readJson("src/test/resources/TestData.json", TestData.class);

    @DataProvider
    public Object[][] CreateUser() {
        return new Object[][]{
                { new User(testData.newUser.username, testData.newUser.job), expectedStatusCodes.create }
        };
    }

    @DataProvider
    public Object[][] UpdateUser() {
        return new Object[][]{
                { testData.updateUserId, new User(testData.newUser.username, testData.newUser.job), expectedStatusCodes.update }
        };
    }

    @DataProvider
    public Object[][] DeleteUser() {
        return new Object[][]{
                { testData.deleteUserId, expectedStatusCodes.delete }
        };
    }

    @DataProvider
    public Object[][] GetUser() {
        return new Object[][]{
                { testData.getUserId,testData.page ,expectedStatusCodes.get }
        };
    }
}

