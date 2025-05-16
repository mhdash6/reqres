package testclasses.features;

import PageComponents.AboutUs;
import com.demoblaze.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import static utilities.common.assertions.AssertionManager.assertFalse;
import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature("About Us Feature")
public class AboutUsTests {


    @Story("Open About Us Page And Find No Errors")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies that the About Us page opens correctly and no error messages are present.")
    @Test(groups = {"Smoke", "Unit"})
    public void AboutUsNoErrors(){
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        String errorMsg=aboutUs.getErrorMsg();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(errorMsg.isEmpty(),errorMsg);
    }

    @Story("Close About Us Page")
    @Severity(SeverityLevel.MINOR)
    @Description("Verifies that the About Us modal can be closed and is no longer visible.")
    @Test(groups = {"Unit"})
    public void AboutUsClosable(){
        HomePage homePage = new HomePage();
        homePage.load();
        AboutUs<HomePage> aboutUs= homePage.navBar.clickAboutUs();
        aboutUs.clickClose();
        assertFalse(aboutUs.isDisplayed(),"About us is still displayed.");
    }

}
