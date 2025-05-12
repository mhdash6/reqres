package testclasses.unit;

import PageComponents.LoginForm;
import PageComponents.SignUpForm;
import com.demoblaze.HomePage;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature( "Sign Up Feature")
public class SignUpTests {
    String username;
    String password;

    public SignUpTests(Faker faker) {
       username = faker.name().firstName().toLowerCase() +
                "_" + faker.name().lastName().toLowerCase() +
                faker.number().numberBetween(1000, 9999);
       password = faker.internet().password();
    }

    @Story( "Sign up successfully with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that users can sign up successfully with valid credentials.")
    @Test(groups = {"Smoke", "Unit"})
    public void signUpWithValidCredentials() {
       HomePage homePage = new HomePage();
       homePage.load();
       SignUpForm<HomePage> signUpForm= homePage.navBar.clickSignUp();
       signUpForm.enterUserName(username);
       signUpForm.enterPassword(password);
       homePage= signUpForm.clickSignUp();
       signUpForm.acceptAlert();
       LoginForm<HomePage> loginForm = homePage.navBar.clickLogin();
       homePage= loginForm.login(username,password);
       assertTrue(homePage.navBar.isLoggedIn());
    }

    @Story( "Reject sign up with existing credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that sign up is rejected when using existing user credentials")
    @Test(groups = {"Smoke", "Unit"} , dependsOnMethods ={"signUpWithValidCredentials"})
    public void signUpWithInvalidCredentials() {
       HomePage homePage = new HomePage();
       homePage.load();
       SignUpForm<HomePage> signUpForm= homePage.navBar.clickSignUp();
       signUpForm.enterUserName(username);
       signUpForm.enterPassword(password);
       signUpForm.clickSignUp();
       assertTrue(signUpForm.isInvalidUsername());
    }


}
