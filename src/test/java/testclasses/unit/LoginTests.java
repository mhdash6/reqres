package testclasses.unit;

import PageComponents.LoginForm;
import com.demoblaze.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.models.LoginTestData;

import java.util.List;

public class LoginTests {
    LoginTestData validCredentials;
    LoginTestData inValidUserName;
    LoginTestData inValidPassword;

    public LoginTests(List<LoginTestData> data){
        validCredentials = data.getFirst();
        inValidUserName = data.get(1);
        inValidPassword = data.getLast();
    }

    @Test
    public void loginWithValidCredentials(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        homePage= loginForm.login(validCredentials.username,validCredentials.password);
        softAssert.assertTrue(homePage.navBar.isLoggedIn());
        softAssert.assertAll();
    }
    @Test
    public void loginWithInvalidUsername(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.login(inValidUserName.username,inValidUserName.password);
        softAssert.assertTrue(loginForm.isInvalidUsername());
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.login(inValidPassword.username,inValidPassword.password);
        softAssert.assertTrue(loginForm.isInvalidPassword());
        softAssert.assertAll();
    }



    @Test
    public void loginWithEmptyCredentials(){
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.clickLogin();
        softAssert.assertTrue(loginForm.isEmptyCredentials());
    }
}
