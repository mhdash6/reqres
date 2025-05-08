package testclasses.unit;

import PageComponents.LoginForm;
import com.demoblaze.HomePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import utils.models.LoginTestData;

import java.util.List;

import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature("Login Feature")
public class LoginTests {
    LoginTestData validCredentials;
    LoginTestData inValidUserName;
    LoginTestData inValidPassword;

    public LoginTests(List<LoginTestData> data){
        validCredentials = data.getFirst();
        inValidUserName = data.get(1);
        inValidPassword = data.getLast();
    }

    @Story("Log in successfully with valid credentials")
    @Test(groups = {"Smoke","Unit"})
    public void loginWithValidCredentials(){
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        homePage= loginForm.login(validCredentials.username,validCredentials.password);
        assertTrue(homePage.navBar.isLoggedIn());
    }

    @Story("Reject login with incorrect username")
    @Test(groups = "Unit")
    public void loginWithInvalidUsername(){
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.login(inValidUserName.username,inValidUserName.password);
        assertTrue(loginForm.isInvalidUsername());
    }

    @Story("Reject login with incorrect password")
    @Test(groups = "Unit")
    public void loginWithInvalidPassword(){
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.login(inValidPassword.username,inValidPassword.password);
        assertTrue(loginForm.isInvalidPassword());
    }

    @Story("Prevent login with empty credentials")
    @Test(groups = "Unit")
    public void loginWithEmptyCredentials(){
        HomePage homePage = new HomePage();
        homePage.load();
        LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
        loginForm.clickLogin();
        assertTrue(loginForm.isEmptyCredentials());
    }
}
